package com.kaspersky.adbserver.device

import com.kaspersky.adbserver.common.api.Command
import com.kaspersky.adbserver.common.api.CommandResult
import com.kaspersky.adbserver.connection.api.ConnectionClient
import com.kaspersky.adbserver.connection.api.ConnectionClientLifecycle
import com.kaspersky.adbserver.connection.api.ConnectionFactory
import com.kaspersky.adbserver.common.api.ExecutorResultStatus
import com.kaspersky.adbserver.common.log.logger.Logger
import com.kaspersky.adbserver.desdevconnection.DesktopDeviceSocketConnectionFactory
import com.kaspersky.adbserver.desdevconnection.DesktopDeviceSocketConnectionType
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

internal class Device private constructor(
    private val connectionClient: ConnectionClient,
    private val logger: Logger
) {

    companion object {
        private const val CONNECTION_ESTABLISH_TIMEOUT_SEC = 5L
        private const val CONNECTION_WAIT_MS = 200L

        fun create(logger: Logger): Device {
            val desktopDeviceSocketConnection =
                DesktopDeviceSocketConnectionFactory.getSockets(
                    DesktopDeviceSocketConnectionType.FORWARD
                )
            val connectionClientLifecycle = object : ConnectionClientLifecycle {
                override fun onDisconnectedBySocketProblems() {
                    logger.i(
                        "The socket connection was interrupted. " +
                                "The possible reason is the Desktop was killed"
                    )
                }
            }
            val connectionClient = ConnectionFactory.createClient(
                desktopDeviceSocketConnection.getDeviceSocketLoad(logger),
                logger,
                connectionClientLifecycle
            )
            return Device(connectionClient, logger)
        }
    }

    private val isRunning = AtomicBoolean(false)
    private val startScanningTrigger = AtomicBoolean(false)

    fun startConnectionToDesktop() {
        if (isRunning.compareAndSet(false, true)) {
            logger.i("User called a start of connection to Desktop")
            WatchdogThread().start()
        }
    }

    fun stopConnectionToDesktop() {
        if (isRunning.compareAndSet(true, false)) {
            logger.i("User called a stop of connection to Desktop")
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
        logger.i("Start to execute the command=$command")
        val commandResult = try {
            awaitConnectionEstablished(CONNECTION_ESTABLISH_TIMEOUT_SEC, TimeUnit.SECONDS)
            connectionClient.executeCommand(command)
        } catch (exception: ConnectionTimeException) {
            CommandResult(
                ExecutorResultStatus.TIMEOUT,
                "The time for the connection establishment is over"
            )
        }
        logger.i("The result of command=$command => $commandResult")
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

    private inner class WatchdogThread : Thread(
        "Connection watchdog thread from Device to Desktop"
    ) {
        override fun run() {
            logger.i("WatchdogThread is started from Device to Desktop")
            while (isRunning.get()) {
                if (!connectionClient.isConnected()) {
                    try {
                        if (startScanningTrigger.compareAndSet(false, true)) {
                            logger.i(
                                "Device tries to connect to the Desktop. " +
                                        "It may take time because the Desktop can be not ready " +
                                        "(for example, there is no active Desktop instance in the local network)."
                            )
                        }
                        connectionClient.tryConnect()
                        if (connectionClient.isConnected()) {
                            startScanningTrigger.set(false)
                            logger.i("The attempt to connect to Desktop was success")
                        }
                    } catch (exception: Exception) {
                        logger.i("The attempt to connect to Desktop was with exception: $exception")
                    }
                }
            }
        }
    }
}
