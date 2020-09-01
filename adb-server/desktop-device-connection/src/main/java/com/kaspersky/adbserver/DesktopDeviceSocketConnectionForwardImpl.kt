package com.kaspersky.adbserver

import com.kaspersky.adbserver.api.CommandExecutor
import com.kaspresky.adbserver.log.LoggerFactory
import java.net.ServerSocket
import java.net.Socket
import kotlin.random.Random

internal class DesktopDeviceSocketConnectionForwardImpl(
    deviceName: String?
) : DesktopDeviceSocketConnection {

    companion object {
        private const val DEVICE_PORT: Int = 8500
        private const val MIN_CLIENT_PORT: Int = 6000
        private const val MAX_CLIENT_PORT: Int = 49000
        private const val LOCAL_HOST: String = "127.0.0.1"
    }

    private val desktopLogger = LoggerFactory.getLogger(tag = javaClass.simpleName, deviceName = deviceName)
    private val deviceLogger = LoggerFactory.getLogger(tag = javaClass.simpleName)
    private val clientPortsList: MutableList<Int> = mutableListOf()

    override fun getDesktopSocketLoad(executor: CommandExecutor): () -> Socket {
        val clientPort = getFreePort()
        desktopLogger.i("getDesktopSocketLoad", "calculated desktop client port=$clientPort")
        forwardPorts(
            executor,
            clientPort,
            DEVICE_PORT
        )
        desktopLogger.i("getDesktopSocketLoad", "desktop client port=$clientPort is forwarding with device server port=$DEVICE_PORT")
        return {
            desktopLogger.i("getDesktopSocketLoad", "started with ip=$LOCAL_HOST, port=$clientPort")
            val readyClientSocket = Socket(LOCAL_HOST, clientPort)
            desktopLogger.i("getDesktopSocketLoad", "completed with ip=$LOCAL_HOST, port=$clientPort")
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

    private fun forwardPorts(executor: CommandExecutor, fromPort: Int, toPort: Int) {
        desktopLogger.i("forwardPorts(fromPort=$fromPort, toPort=$toPort)", "started")
        val result = executor.execute(AdbCommand("forward tcp:$fromPort tcp:$toPort"))
        desktopLogger.i("forwardPorts(fromPort=$fromPort, toPort=$toPort)", "result=$result")
    }

    override fun getDeviceSocketLoad(): () -> Socket = {
        deviceLogger.i("getDeviceSocketLoad", "started")
        val serverSocket = ServerSocket(DEVICE_PORT)
        val readyServerSocket = serverSocket.accept()
        deviceLogger.i("getDeviceSocketLoad", "completed")
        readyServerSocket
    }
}
