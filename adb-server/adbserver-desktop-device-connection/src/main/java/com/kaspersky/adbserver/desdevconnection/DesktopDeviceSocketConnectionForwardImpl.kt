package com.kaspersky.adbserver.desdevconnection

import com.kaspersky.adbserver.common.api.CommandExecutor
import com.kaspersky.adbserver.commandtypes.AdbCommand
import com.kaspersky.adbserver.common.log.logger.Logger
import java.net.ServerSocket
import java.net.Socket
import kotlin.random.Random

internal class DesktopDeviceSocketConnectionForwardImpl : DesktopDeviceSocketConnection {

    companion object {
        private const val DEVICE_PORT: Int = 8500
        private const val MIN_CLIENT_PORT: Int = 6000
        private const val MAX_CLIENT_PORT: Int = 49000
        private const val LOCAL_HOST: String = "127.0.0.1"
    }

    private val clientPortsList: MutableList<Int> = mutableListOf()

    override fun getDesktopSocketLoad(executor: CommandExecutor, logger: Logger): () -> Socket {
        val clientPort = getFreePort()
        logger.d("calculated desktop client port=$clientPort")
        forwardPorts(
            executor,
            clientPort,
            DEVICE_PORT,
            logger
        )
        logger.d("desktop client port=$clientPort is forwarding with device server port=$DEVICE_PORT")
        return {
            logger.d("started with ip=$LOCAL_HOST, port=$clientPort")
            val readyClientSocket = Socket(LOCAL_HOST, clientPort)
            logger.d("completed with ip=$LOCAL_HOST, port=$clientPort")
            readyClientSocket
        }
    }

    private fun getFreePort(): Int {
        var newClientPort: Int
        while (true) {
            newClientPort = Random.Default.nextInt(
                MIN_CLIENT_PORT,
                MAX_CLIENT_PORT
            )
            if (!clientPortsList.contains(newClientPort)) {
                break
            }
            clientPortsList.add(newClientPort)
        }
        return newClientPort
    }

    private fun forwardPorts(executor: CommandExecutor, fromPort: Int, toPort: Int, logger: Logger) {
        logger.d("fromPort=$fromPort, toPort=$toPort started")
        val result = executor.execute(AdbCommand("forward tcp:$fromPort tcp:$toPort"))
        logger.d("fromPort=$fromPort, toPort=$toPort) finished with result=$result")
    }

    override fun getDeviceSocketLoad(logger: Logger): () -> Socket = {
        logger.d("Started")
        val serverSocket = ServerSocket(DEVICE_PORT)
        val readyServerSocket = serverSocket.accept()
        logger.d("Completed")
        readyServerSocket
    }
}
