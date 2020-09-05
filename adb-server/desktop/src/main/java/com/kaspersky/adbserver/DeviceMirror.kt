package com.kaspersky.adbserver

import com.kaspersky.adbserver.api.Command
import com.kaspersky.adbserver.api.CommandResult
import com.kaspersky.adbserver.api.ConnectionServer
import com.kaspersky.adbserver.api.ConnectionServerLifecycle
import com.kaspersky.adbserver.api.ConnectionFactory
import com.kaspresky.adbserver.log.logger.Logger
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicReference

internal class DeviceMirror private constructor(
    private val connectionServer: ConnectionServer,
    internal val deviceName: String,
    private val logger: Logger
) {

    companion object {
        private const val CONNECTION_WAIT_MS = 2_000L

        fun create(
            cmdCommandPerformer: CmdCommandPerformer,
            deviceName: String,
            adbServerPort: String?,
            logger: Logger
        ): DeviceMirror {
            val desktopDeviceSocketConnection =
                DesktopDeviceSocketConnectionFactory.getSockets(DesktopDeviceSocketConnectionType.FORWARD)
            val commandExecutor = CommandExecutorImpl(
                cmdCommandPerformer,
                deviceName,
                adbServerPort,
                logger
            )
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
                desktopDeviceSocketConnection.getDesktopSocketLoad(commandExecutor, logger),
                commandExecutor,
                logger,
                connectionServerLifecycle
            )
            return DeviceMirror(
                connectionServer,
                deviceName,
                logger
            )
        }
    }

    private val isRunning = AtomicReference<Boolean>()
    private val startScanningTrigger = AtomicBoolean(false)

    fun startConnectionToDevice() {
        logger.i("The connection establishment to device started")
        isRunning.set(true)
        WatchdogThread().start()
    }

    fun stopConnectionToDevice() {
        logger.i("The connection interruption to device started")
        isRunning.set(false)
        connectionServer.tryDisconnect()
        logger.i("The connection interruption to device completed")
    }

    private inner class WatchdogThread : Thread() {
        override fun run() {
            logger.i("WatchdogThread is started from Desktop to Device")
            while (isRunning.get()) {
                if (!connectionServer.isConnected()) {
                    try {
                        logger.d("The attempt to connect to Device. " +
                                "It may take time because the device can be not ready (for example, a kaspresso test was not started).")
                        if (startScanningTrigger.compareAndSet(false, true)) {
                            logger.i("Desktop tries to connect to the Device. " +
                                    "It may take time because the device can be not ready (for example, a kaspresso test was not started).")
                        }
                        connectionServer.tryConnect()
                        if (connectionServer.isConnected()) {
                            startScanningTrigger.set(false)
                            logger.i("The attempt to connect to Device was success")
                        }
                    } catch (exception: Exception) {
                        logger.i("The attempt to connect to Device failed with exception: $exception")
                    }
                }
                sleep(CONNECTION_WAIT_MS)
            }
        }
    }
}
