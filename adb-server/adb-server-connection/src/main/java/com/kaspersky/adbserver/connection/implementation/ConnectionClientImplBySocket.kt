package com.kaspersky.adbserver.connection.implementation

import com.kaspersky.adbserver.common.api.Command
import com.kaspersky.adbserver.common.api.CommandResult
import com.kaspersky.adbserver.connection.api.ConnectionClient
import com.kaspersky.adbserver.connection.api.ConnectionClientLifecycle
import com.kaspersky.adbserver.common.api.ExecutorResultStatus
import com.kaspersky.adbserver.connection.implementation.lightsocket.LightSocketWrapperImpl
import com.kaspersky.adbserver.connection.implementation.transferring.ExpectedEOFException
import com.kaspersky.adbserver.connection.implementation.transferring.ResultMessage
import com.kaspersky.adbserver.connection.implementation.transferring.SocketMessagesTransferring
import com.kaspersky.adbserver.connection.implementation.transferring.TaskMessage
import com.kaspersky.adbserver.common.log.logger.Logger
import java.net.Socket
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit

internal class ConnectionClientImplBySocket(
    private val socketCreation: () -> Socket,
    private val logger: Logger,
    private val connectionClientLifecycle: ConnectionClientLifecycle
) : ConnectionClient {

    companion object {
        private val COMMAND_TIMEOUT_MIN = TimeUnit.MINUTES.toSeconds(3)
    }

    private var _socket: Socket? = null
    private val socket: Socket
        get() = _socket
            ?: throw IllegalStateException("Socket is not initialised. Please call `tryConnect` function at first.")

    private var _socketMessagesTransferring: SocketMessagesTransferring<ResultMessage<CommandResult>, TaskMessage>? =
        null
    private val socketMessagesTransferring: SocketMessagesTransferring<ResultMessage<CommandResult>, TaskMessage>
        get() = _socketMessagesTransferring
            ?: throw IllegalStateException("Socket transferring is not initialised. Please call `tryConnect` function at first.")

    private var connectionMaker: ConnectionMaker = ConnectionMaker(logger)
    private val commandsInProgress =
        ConcurrentHashMap<Command, ResultWaiter<ResultMessage<CommandResult>>>()

    override fun tryConnect() {
        logger.d("Start the process")
        connectionMaker.connect(
            connectAction = {
                _socket = socketCreation.invoke()
                _socketMessagesTransferring = SocketMessagesTransferring.createTransferring(
                    lightSocketWrapper = LightSocketWrapperImpl(socket),
                    disruptAction = {
                        tryDisconnectBySocketProblems()
                        connectionClientLifecycle.onDisconnectedBySocketProblems()
                    },
                    logger = logger
                )
                socketMessagesTransferring.prepareListening()
            },
            successConnectAction = {
                logger.d("The connection is ready. Start messages listening")
                handleMessages()
            },
            failureConnectAction = { exception ->
                _socket?.close()
                _socket = null
                _socketMessagesTransferring = null

                val errorReasonMessage = if (exception is ExpectedEOFException)
                    "The most possible reason is the opposite socket is not ready yet"
                else "The exception=$exception"
                logger.d("The connection establishment attempt failed. \n$errorReasonMessage")
            }
        )
    }

    private fun handleMessages() {
        socketMessagesTransferring.startListening { resultMessage ->
            logger.d("Received resultMessage=$resultMessage")
            commandsInProgress[resultMessage.command]?.latchResult(resultMessage)
        }
    }

    private fun tryDisconnectBySocketProblems() {
        logger.d("Start the process")
        val failureReason =
            "There was some problem inside a Socket creation process or during a Socket connection. \n" +
                    "The most possible reason is using of old version of 'adbserver-desktop.jar (desktop.jar)'. \n" +
                    "Please, use the most modern version of 'adbserver-desktop.jar' located in https://github.com/KasperskyLab/Kaspresso/tree/master/artifacts."
        tryDisconnectCommon(failureReason)
        logger.d("The disconnection was completed")
    }

    override fun tryDisconnect() {
        logger.d("Start the process")
        val failureReason =
            "`ConnectionClientImplBySocket.tryDisconnect` function was called earlier. " +
                    "Please, observe code points and relative logs where this function started."
        tryDisconnectCommon(failureReason)
        logger.d("The disconnection was completed")
    }

    private fun tryDisconnectCommon(failureReason: String) {
        connectionMaker.disconnect {
            _socketMessagesTransferring?.stopListening()
            _socket?.close()
            resetCommandsInProgress(failureReason)
        }
    }

    private fun resetCommandsInProgress(failureReason: String) {
        for ((adbCommand, resultWaiter) in commandsInProgress) {
            val commandResult = CommandResult(ExecutorResultStatus.FAILURE, failureReason)
            logger.d(
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
        logger.d("Started command=$command")

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
                ExecutorResultStatus.FAILURE,
                "Waiting thread was interrupted"
            )
            logger.d("Command=$command failed with commandResult=$failedCommandResult")
            return failedCommandResult
        } finally {
            commandsInProgress.remove(command)
        }

        if (resultMessage == null) {
            val failedCommandResult = CommandResult(
                ExecutorResultStatus.FAILURE,
                "Waiting result timeout was expired"
            )
            logger.d("Command=$command failed with commandResult=$failedCommandResult")
            return failedCommandResult
        }
        logger.d("Command=$command completed with commandResult=${resultMessage.data}")

        return resultMessage.data
    }
}
