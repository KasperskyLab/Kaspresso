package com.kaspersky.adbserver

import com.kaspersky.adbserver.api.Command
import com.kaspersky.adbserver.api.CommandResult
import com.kaspersky.adbserver.api.ConnectionClient
import com.kaspersky.adbserver.api.ConnectionFactory
import com.kaspersky.adbserver.api.ExecutorResultStatus
import com.kaspresky.adbserver.log.LoggerFactory
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean

internal class Device private constructor(
    private val connectionClient: ConnectionClient
) {

    companion object {
        private const val CONNECTION_ESTABLISH_TIMEOUT_SEC = 5L
        private const val CONNECTION_WAIT_MS = 200L

        fun create(): Device {
            val desktopDeviceSocketConnection =
                DesktopDeviceSocketConnectionFactory.getSockets(
                    DesktopDeviceSocketConnectionType.FORWARD
                )
            val connectionClient = ConnectionFactory.createClient(
                desktopDeviceSocketConnection.getDeviceSocketLoad()
            )
            return Device(connectionClient)
        }
    }

    private val logger = LoggerFactory.getLogger(tag = javaClass.simpleName)
    private val isRunning = AtomicBoolean(false)

    fun startConnectionToDesktop() {
        if (isRunning.compareAndSet(false, true)) {
            logger.i("start", "start")
            WatchdogThread().start()
        }
    }

    fun stopConnectionToDesktop() {
        if (isRunning.compareAndSet(true, false)) {
            logger.i("stop", "stop")
            connectionClient.tryDisconnect()
        }
    }

    /**
     * Please, be aware!
     * It's a synchronous and time-consuming method.
     * This method includes:
     * 1. a waiting time of the connection establishment (if it has not been yet)
     * 2. the adb command execution time
     */
    fun fulfill(command: Command): CommandResult {
        logger.i("execute", "Start to execute the command=$command")
        val commandResult = try {
            awaitConnectionEstablished(CONNECTION_ESTABLISH_TIMEOUT_SEC, TimeUnit.SECONDS)
            connectionClient.executeCommand(command)
        } catch (exception: ConnectionTimeException) {
            CommandResult(
                ExecutorResultStatus.TIMEOUT,
                "The time for the connection establishment is over"
            )
        }
        logger.i("execute", "The result of command=$command => $commandResult")
        return commandResult
    }

    @Throws(ConnectionTimeException::class)
    private fun awaitConnectionEstablished(timeout: Long, timeUnit: TimeUnit) {
        val timeoutMs = timeUnit.toMillis(timeout)
        var waitTime = 0L
        while (!connectionClient.isConnected() && waitTime <= timeoutMs) {
            Thread.sleep(CONNECTION_WAIT_MS)
            waitTime += CONNECTION_WAIT_MS
        }
        if (!connectionClient.isConnected()) {
            throw ConnectionTimeException(
                "The time (timeout=$timeout, timeUnit=$timeUnit) for the connection establishment is over"
            )
        }
    }

    // todo get name of the device?
    private inner class WatchdogThread : Thread("Connection watchdog thread from Device to Desktop") {
        override fun run() {
            logger.i("WatchdogThread.run", "WatchdogThread starts from Device to Desktop")
            while (isRunning.get()) {
                if (!connectionClient.isConnected()) {
                    try {
                        logger.i("WatchdogThread.run", "Try to connect to Desktop...")
                        connectionClient.tryConnect()
                    } catch (exception: Exception) {
                        logger.i("WatchdogThread.run", "The attempt to connect to Desktop was with exception: $exception")
                    }
                }
            }
        }
    }
}
