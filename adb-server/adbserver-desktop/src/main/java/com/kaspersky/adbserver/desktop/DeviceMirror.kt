package com.kaspersky.adbserver.desktop

import com.kaspersky.adbserver.desdevconnection.DesktopDeviceSocketConnectionFactory
import com.kaspersky.adbserver.desdevconnection.DesktopDeviceSocketConnectionType
import com.kaspersky.adbserver.common.api.Command
import com.kaspersky.adbserver.common.api.CommandResult
import com.kaspersky.adbserver.connection.api.ConnectionServer
import com.kaspersky.adbserver.connection.api.ConnectionServerLifecycle
import com.kaspersky.adbserver.connection.api.ConnectionFactory
import com.kaspersky.adbserver.common.log.logger.Logger
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicReference

internal class DeviceMirror private constructor(
    private val connectionServer: ConnectionServer,
    internal val deviceName: String,
    private val logger: Logger
) {

    companion object {
        private const val WATCHDOG_CONNECTION_WAIT_MS = 1_000L

        fun create(
            cmdCommandPerformer: CmdCommandPerformer,
            deviceName: String,
            adbServerPort: String?,
            logger: Logger,
            adbPath: String
        ): DeviceMirror {
            val desktopDeviceSocketConnection =
                DesktopDeviceSocketConnectionFactory.getSockets(DesktopDeviceSocketConnectionType.FORWARD)
            val commandExecutor = CommandExecutorImpl(
                cmdCommandPerformer,
                deviceName,
                adbServerPort,
                logger,
                adbPath
            )
            val connectionServerLifecycle = object : ConnectionServerLifecycle {
                override fun onReceivedTask(command: Command) {
                    logger.i("The received command to execute: $command")
                }

                override fun onExecutedTask(command: Command, commandResult: CommandResult) {
                    logger.i("The executed command: $command. The result: $commandResult")
                }

                override fun onDisconnectedBySocketProblems() {
                    logger.i(
                        "The socket connection was interrupted. " +
                                "The possible reason is killed Kaspresso application on the device"
                    )
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
                        logger.d(
                            "The attempt to connect to Device. " +
                                    "It may take time because the device can be not ready (for example, a kaspresso test was not started)."
                        )
                        if (startScanningTrigger.compareAndSet(false, true)) {
                            logger.i(
                                "Desktop tries to connect to the Device.\n " +
                                        "It may take time because the device can be not ready. Possible reason: " +
                                        "a kaspresso test has not been started"
                            )
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
                sleep(WATCHDOG_CONNECTION_WAIT_MS)
            }
        }
    }
}
