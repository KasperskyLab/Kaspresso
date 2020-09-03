package com.kaspersky.adbserver.implementation

import com.kaspersky.adbserver.api.Command
import com.kaspersky.adbserver.api.CommandResult
import com.kaspersky.adbserver.api.ConnectionClient
import com.kaspersky.adbserver.api.ExecutorResultStatus
import com.kaspersky.adbserver.implementation.lightsocket.LightSocketWrapperImpl
import com.kaspersky.adbserver.implementation.transferring.ExpectedEOFException
import com.kaspersky.adbserver.implementation.transferring.ResultMessage
import com.kaspersky.adbserver.implementation.transferring.SocketMessagesTransferring
import com.kaspersky.adbserver.implementation.transferring.TaskMessage
import com.kaspresky.adbserver.log.LoggerFactory
import java.net.Socket
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit

internal class ConnectionClientImplBySocket(
    private val socketCreation: () -> Socket
) : ConnectionClient {

    companion object {
        private val COMMAND_TIMEOUT_MIN = TimeUnit.MINUTES.toSeconds(3)
    }

    private val logger = LoggerFactory.getLogger(tag = javaClass.simpleName)

    private var _socket: Socket? = null
    private val socket: Socket
        get() = _socket ?: throw IllegalStateException("Socket is not initialised. Please call `tryConnect` function at first.")

    private var _socketMessagesTransferring: SocketMessagesTransferring<ResultMessage<CommandResult>, TaskMessage>? = null
    private val socketMessagesTransferring: SocketMessagesTransferring<ResultMessage<CommandResult>, TaskMessage>
        get() = _socketMessagesTransferring ?: throw IllegalStateException("Socket transferring is not initialised. Please call `tryConnect` function at first.")

    private var connectionMaker: ConnectionMaker = ConnectionMaker()
    private val commandsInProgress = ConcurrentHashMap<Command, ResultWaiter<ResultMessage<CommandResult>>>()

    override fun tryConnect() {
        logger.d("tryConnect", "Start the process")
        connectionMaker.connect(
            connectAction = {
                _socket = socketCreation.invoke()
                _socketMessagesTransferring = SocketMessagesTransferring.createTransferring(
                    lightSocketWrapper = LightSocketWrapperImpl(socket),
                    disruptAction = { tryDisconnectBySocketProblems() }
                )
                socketMessagesTransferring.prepareListening()
            },
            successConnectAction = {
                logger.d("tryConnect", "The connection is ready. Start messages listening")
                handleMessages()
            },
            failureConnectAction = { exception ->
                _socket = null
                _socketMessagesTransferring = null

                val errorReasonMessage = if (exception is ExpectedEOFException)
                    "The most possible reason is the opposite socket is not ready yet"
                else "The exception=$exception"
                logger.d("tryConnect", "The connection establishment attempt failed. \n$errorReasonMessage")
            }
        )
    }

    private fun handleMessages() {
        socketMessagesTransferring.startListening { resultMessage ->
            logger.d("handleMessages", "Received resultMessage=$resultMessage")
            commandsInProgress[resultMessage.command]?.latchResult(resultMessage)
        }
    }

    private fun tryDisconnectBySocketProblems() {
        logger.d("tryDisconnectBySocketProblems", "Start the process")
        val failureReason = "There was some problem inside a Socket creation process or during a Socket connection. \n" +
                "The most possible reason is using of old version of 'desktop.jar'. \n" +
                "Please, use the most modern version of 'desktop.jar' located in https://github.com/KasperskyLab/Kaspresso/tree/master/artifacts."
        tryDisconnectCommon(failureReason)
        logger.d("tryDisconnectBySocketProblems", "The disconnection was completed")
    }

    override fun tryDisconnect() {
        logger.d("tryDisconnect", "Start the process")
        val failureReason = "`ConnectionClientImplBySocket.tryDisconnect` function was called earlier. " +
                "Please, observe code points and relative logs where this function started."
        tryDisconnectCommon(failureReason)
        logger.d("tryDisconnect", "The disconnection was completed")
    }

    private fun tryDisconnectCommon(failureReason: String) {
        connectionMaker.disconnect {
            socketMessagesTransferring.stopListening()
            socket.close()
            resetCommandsInProgress(failureReason)
        }
    }

    private fun resetCommandsInProgress(failureReason: String) {
        for ((adbCommand, resultWaiter) in commandsInProgress) {
            val commandResult = CommandResult(ExecutorResultStatus.FAILED, failureReason)
            logger.d(
                "resetCommandsInProgress",
                "The command=$adbCommand was failed because the socket connection had broken up. \n" +
                                    "Result=$commandResult"
            )
            resultWaiter.latchResult(
                ResultMessage(
                    adbCommand,
                    commandResult
                )
            )
        }
        commandsInProgress.clear()
    }

    override fun isConnected(): Boolean =
        connectionMaker.isConnected()

    @Suppress("ReturnCount")
    override fun executeCommand(command: Command): CommandResult {
        logger.d("executeCommand", "Started command=$command")

        val resultWaiter = ResultWaiter<ResultMessage<CommandResult>>()
        commandsInProgress[command] = resultWaiter
        socketMessagesTransferring.sendMessage(
            TaskMessage(command)
        )

        val resultMessage: ResultMessage<CommandResult>?
        try {
            resultMessage = resultWaiter.waitResult(COMMAND_TIMEOUT_MIN, TimeUnit.SECONDS)
        } catch (exception: InterruptedException) {
            val failedCommandResult = CommandResult(
                ExecutorResultStatus.FAILED,
                "Waiting thread was interrupted"
            )
            logger.d("executeCommand", "Command=$command failed with commandResult=$failedCommandResult")
            return failedCommandResult
        } finally {
            commandsInProgress.remove(command)
        }

        if (resultMessage == null) {
            val failedCommandResult = CommandResult(
                ExecutorResultStatus.FAILED,
                "Waiting result timeout was expired"
            )
            logger.d("executeCommand", "command=$command failed with commandResult=$failedCommandResult")
            return failedCommandResult
        }
        logger.d("executeCommand", "command=$command completed with commandResult=${resultMessage.data}")

        return resultMessage.data
    }
}
