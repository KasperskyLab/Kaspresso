package com.kaspersky.adbserver.connection.implementation

import com.kaspersky.adbserver.common.api.CommandExecutor
import com.kaspersky.adbserver.common.api.CommandResult
import com.kaspersky.adbserver.connection.api.ConnectionServer
import com.kaspersky.adbserver.connection.api.ConnectionServerLifecycle
import com.kaspersky.adbserver.connection.implementation.lightsocket.LightSocketWrapperImpl
import com.kaspersky.adbserver.connection.implementation.transferring.ExpectedEOFException
import com.kaspersky.adbserver.connection.implementation.transferring.ResultMessage
import com.kaspersky.adbserver.connection.implementation.transferring.SocketMessagesTransferring
import com.kaspersky.adbserver.connection.implementation.transferring.TaskMessage
import com.kaspersky.adbserver.common.log.logger.Logger
import java.net.Socket
import java.util.concurrent.Executors

internal class ConnectionServerImplBySocket(
    private val socketCreation: () -> Socket,
    private val commandExecutor: CommandExecutor,
    private val logger: Logger,
    private val connectionServerLifecycle: ConnectionServerLifecycle
) : ConnectionServer {

    private var connectionMaker: ConnectionMaker = ConnectionMaker(logger)

    private var _socket: Socket? = null
    private val socket: Socket
        get() = _socket ?: throw IllegalStateException("Socket is not initialised. Please call `tryConnect` function at first.")

    private var _socketMessagesTransferring: SocketMessagesTransferring<TaskMessage, ResultMessage<CommandResult>>? = null
    private val socketMessagesTransferring: SocketMessagesTransferring<TaskMessage, ResultMessage<CommandResult>>
        get() = _socketMessagesTransferring ?: throw IllegalStateException("Socket transferring is not initialised. Please call `tryConnect` function at first.")

    private val backgroundExecutor = Executors.newCachedThreadPool()

    override fun tryConnect() {
        logger.d("Start the process")
        connectionMaker.connect(
            connectAction = {
                _socket = socketCreation.invoke()
                _socketMessagesTransferring = SocketMessagesTransferring.createTransferring(
                    lightSocketWrapper = LightSocketWrapperImpl(socket),
                    disruptAction = {
                        tryDisconnect()
                        connectionServerLifecycle.onDisconnectedBySocketProblems()
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
                logger.d("The connection establishment attempt failed. $errorReasonMessage")
            }
        )
    }

    private fun handleMessages() {
        socketMessagesTransferring.startListening { taskMessage ->
            connectionServerLifecycle.onReceivedTask(taskMessage.command)
            logger.d("Received taskMessage=$taskMessage")
            backgroundExecutor.execute {
                val result = commandExecutor.execute(taskMessage.command)
                connectionServerLifecycle.onExecutedTask(taskMessage.command, result)
                logger.d("Result of taskMessage=$taskMessage => result=$result")
                socketMessagesTransferring.sendMessage(
                    ResultMessage(
                        taskMessage.command,
                        result
                    )
                )
            }
        }
    }

    override fun tryDisconnect() {
        logger.d("Start the process")
        connectionMaker.disconnect {
            socketMessagesTransferring.stopListening()
            socket.close()
        }
        logger.d("Success disconnection")
    }

    override fun isConnected(): Boolean =
        connectionMaker.isConnected()
}
