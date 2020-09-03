package com.kaspersky.adbserver

import com.kaspersky.adbserver.api.*
import com.kaspresky.adbserver.log.LoggerFactory
import com.kaspresky.adbserver.log.logger.Logger
import java.util.concurrent.atomic.AtomicReference

internal class DeviceMirror private constructor(
    val deviceName: String,
    private val logger: Logger,
    private val connectionServer: ConnectionServer
) {

    companion object {
        private const val CONNECTION_WAIT_MS = 2_000L

        fun create(
            deviceName: String,
            adbServerPort: String?,
            cmdCommandPerformer: CmdCommandPerformer,
            desktopName: String
        ): DeviceMirror {
            val desktopDeviceSocketConnection =
                DesktopDeviceSocketConnectionFactory.getSockets(
                    DesktopDeviceSocketConnectionType.FORWARD,
                    deviceName
                )
            val commandExecutor = CommandExecutorImpl(
                cmdCommandPerformer,
                deviceName,
                adbServerPort
            )
            val logger = LoggerFactory.getLogger(tag = "DeviceMirror", deviceName = deviceName)
            val connectionServerLifecycle = object : ConnectionServerLifecycle {
                override fun onReceivedTask(command: Command) {
                    logger.i("The received command to execute: $command")
                }
                override fun onExecutedTask(command: Command, commandResult: CommandResult) {
                    logger.i("The executed command: $command. The result: $commandResult")
                }
                override fun onDisconnectedBySocketProblems() {
                    logger.i("The socket connection was interrupted. " +
                            "The possible reason is killed Kaspresso application on the device")
                }
            }
            val connectionServer = ConnectionFactory.createServer(
                desktopDeviceSocketConnection.getDesktopSocketLoad(commandExecutor),
                commandExecutor,
                deviceName,
                desktopName,
                connectionServerLifecycle
            )
            return DeviceMirror(
                deviceName,
                logger,
                connectionServer
            )
        }
    }

    private val isRunning = AtomicReference<Boolean>()

    fun startConnectionToDevice() {
        logger.i("The connection establishment to device=$deviceName started")
        isRunning.set(true)
        WatchdogThread().start()
    }

    fun stopConnectionToDevice() {
        logger.i("The connection disconnection to device=$deviceName started")
        isRunning.set(false)
        connectionServer.tryDisconnect()
        logger.i("The connection disconnection to device=$deviceName completed")
    }

    private inner class WatchdogThread : Thread() {
        override fun run() {
            logger.i("WatchdogThread is started from Desktop to Device=$deviceName")
            while (isRunning.get()) {
                if (!connectionServer.isConnected()) {
                    try {
                        logger.d("WatchdogThread.run", "The attempt to connect to Device=$deviceName. " +
                                "It may take time because the device can be not ready (for example, a kaspresso test was not started).")
                        connectionServer.tryConnect()
                        if (connectionServer.isConnected()) {
                            logger.i("The attempt to connect to Device=$deviceName was success")
                        }
                    } catch (exception: Exception) {
                        logger.i("The attempt to connect to Device=$deviceName failed with exception: $exception")
                    }
                }
                sleep(CONNECTION_WAIT_MS)
            }
        }
    }
}
