package com.kaspersky.test_server.implementation

import com.kaspersky.test_server.api.CommandExecutor
import com.kaspersky.test_server.api.CommandResult
import com.kaspersky.test_server.api.ConnectionServer
import com.kaspersky.test_server.implementation.light_socket.LightSocketWrapperImpl
import com.kaspersky.test_server.implementation.transferring.ResultMessage
import com.kaspersky.test_server.implementation.transferring.SocketMessagesTransferring
import com.kaspersky.test_server.implementation.transferring.TaskMessage
import com.kaspresky.test_server.log.LoggerFactory
import java.net.Socket
import java.util.concurrent.Executors

internal class ConnectionServerImplBySocket(
    private val socketCreation: () -> Socket,
    private val commandExecutor: CommandExecutor,
    private val deviceName: String
) : ConnectionServer {

    private val logger = LoggerFactory.getLogger(tag = javaClass.simpleName, deviceName = deviceName)
    private var connectionMaker: ConnectionMaker = ConnectionMaker(deviceName)
    private lateinit var socketMessagesTransferring: SocketMessagesTransferring<TaskMessage, ResultMessage<CommandResult>>

    private var _socket: Socket? = null
    private val socket: Socket
        get() = _socket ?: throw IllegalStateException("tryConnect must be called first")

    private val backgroundExecutor = Executors.newCachedThreadPool()

    override fun tryConnect() {
        logger.i("tryConnect", "start")
        connectionMaker.connect(
            connectAction = { _socket = socketCreation.invoke() },
            successConnectAction = {
                logger.i("tryConnect", "start handleMessages")
                handleMessages()
            }
        )
        logger.i("tryConnect", "attempt completed")
    }

    private fun handleMessages() {
        socketMessagesTransferring = SocketMessagesTransferring.createTransferring(
            lightSocketWrapper = LightSocketWrapperImpl(socket),
            disruptAction = { tryDisconnect() },
            deviceName = deviceName
        )
        socketMessagesTransferring.startListening { taskMessage ->
            logger.i("handleMessages", "received taskMessage=$taskMessage")
            backgroundExecutor.execute {
                val result = commandExecutor.execute(taskMessage.command)
                logger.i("handleMessages.backgroundExecutor", "result of taskMessage=$taskMessage => result=$result")
                socketMessagesTransferring.sendMessage(
                    ResultMessage(taskMessage.command, result)
                )
            }
        }
    }

    override fun tryDisconnect() {
        logger.i("tryDisconnect", "start")
        connectionMaker.disconnect {
            socketMessagesTransferring.stopListening()
            socket.close()
        }
        logger.i("tryDisconnect", "attempt completed")
    }

    override fun isConnected(): Boolean =
        connectionMaker.isConnected()
}