package com.kaspersky.adbserver.desktop

import com.kaspersky.adbserver.common.api.ExecutorResultStatus
import com.kaspersky.adbserver.common.log.LoggerFactory
import com.kaspersky.adbserver.common.log.logger.DesktopLogger
import java.util.regex.Pattern

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

internal class Desktop(
    private val cmdCommandPerformer: CmdCommandPerformer,
    private val presetEmulators: List<String>,
    private val adbServerPort: String?,
    private val logger: DesktopLogger,
    private val adbPath: String
) {

    companion object {
        private const val PAUSE_MS = 500L
    }

    private val devices: MutableCollection<DeviceMirror> = mutableListOf()

    fun startDevicesObserving() {
        logger.d("start")
        while (true) {
            val namesOfAttachedDevicesByAdb = getAttachedDevicesByAdb()
            namesOfAttachedDevicesByAdb.forEach { deviceName ->
                if (devices.find { client -> client.deviceName == deviceName } == null) {
                    logger.i("New device has been found: $deviceName. Initialize connection to the device...")
                    val deviceMirror =
                        DeviceMirror.create(
                            cmdCommandPerformer,
                            deviceName,
                            adbServerPort,
                            LoggerFactory.getDesktopLoggerReflectingDevice(logger, deviceName),
                            adbPath
                        )
                    deviceMirror.startConnectionToDevice()
                    devices += deviceMirror
                }
            }
            devices.removeIf { client ->
                if (client.deviceName !in namesOfAttachedDevicesByAdb) {
                    logger.i("Adb connection to ${client.deviceName} has been missed. Stop connection.")
                    client.stopConnectionToDevice()
                    return@removeIf true
                } else {
                    return@removeIf false
                }
            }
            Thread.sleep(PAUSE_MS)
        }
    }

    private fun getAttachedDevicesByAdb(): List<String> {
        val pattern = Pattern.compile("^([a-zA-Z0-9\\-:.]+)(\\s+)(device)")
        val commandResult = cmdCommandPerformer.perform("$adbPath devices")
        if (commandResult.status != ExecutorResultStatus.SUCCESS) {
            return emptyList()
        }
        val adbDevicesCommandResult: String = commandResult.description
        return adbDevicesCommandResult.lines()
            .asSequence()
            .map { pattern.matcher(it) }
            .filter { matcher -> matcher.find() }
            .map { matcher -> matcher.group(1) }
            .filter { foundEmulator -> presetEmulators.isEmpty() || presetEmulators.contains(foundEmulator) }
            .toList()
    }
}
