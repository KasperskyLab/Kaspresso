package com.kaspersky.adbserver.implementation

import com.kaspersky.adbserver.api.CommandExecutor
import com.kaspersky.adbserver.api.CommandResult
import com.kaspersky.adbserver.api.ConnectionServer
import com.kaspersky.adbserver.implementation.lightsocket.LightSocketWrapperImpl
import com.kaspersky.adbserver.implementation.transferring.ResultMessage
import com.kaspersky.adbserver.implementation.transferring.SocketMessagesTransferring
import com.kaspersky.adbserver.implementation.transferring.TaskMessage
import com.kaspresky.adbserver.log.LoggerFactory
import java.net.Socket
import java.util.concurrent.Executors

internal class ConnectionServerImplBySocket(
    private val socketCreation: () -> Socket,
    private val commandExecutor: CommandExecutor,
    private val deviceName: String
) : ConnectionServer {

    private val logger = LoggerFactory.getLogger(tag = javaClass.simpleName, deviceName = deviceName)
    private var connectionMaker: ConnectionMaker =
        ConnectionMaker(deviceName)
    private lateinit var socketMessagesTransferring: SocketMessagesTransferring<TaskMessage, ResultMessage<CommandResult>>

    private var _socket: Socket? = null
    private val socket: Socket
        get() = _socket ?: throw IllegalStateException("tryConnect must be called first")

    private val backgroundExecutor = Executors.newCachedThreadPool()

    override fun tryConnect() {
        logger.d("tryConnect", "start")
        connectionMaker.connect(
            connectAction = { _socket = socketCreation.invoke() },
            successConnectAction = {
                logger.d("tryConnect", "start handleMessages")
                handleMessages()
            }
        )
        logger.d("tryConnect", "attempt completed")
    }

    private fun handleMessages() {
        socketMessagesTransferring = SocketMessagesTransferring.createTransferring(
            lightSocketWrapper = LightSocketWrapperImpl(
                socket
            ),
            disruptAction = { tryDisconnect() },
            deviceName = deviceName
        )
        socketMessagesTransferring.startListening { taskMessage ->
            logger.d("handleMessages", "received taskMessage=$taskMessage")
            backgroundExecutor.execute {
                val result = commandExecutor.execute(taskMessage.command)
                logger.d("handleMessages.backgroundExecutor", "result of taskMessage=$taskMessage => result=$result")
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
        logger.d("tryDisconnect", "start")
        connectionMaker.disconnect {
            socketMessagesTransferring.stopListening()
            socket.close()
        }
        logger.d("tryDisconnect", "attempt completed")
    }

    override fun isConnected(): Boolean =
        connectionMaker.isConnected()
}
