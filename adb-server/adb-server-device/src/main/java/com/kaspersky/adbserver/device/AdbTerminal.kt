package com.kaspersky.adbserver.device

import com.kaspersky.adbserver.commandtypes.AdbCommand
import com.kaspersky.adbserver.commandtypes.CmdCommand
import com.kaspersky.adbserver.commandtypes.ComplexAdbCommand
import com.kaspersky.adbserver.common.api.CommandResult
import com.kaspersky.adbserver.common.log.LoggerFactory
import com.kaspersky.adbserver.common.log.logger.LogLevel
import com.kaspersky.adbserver.common.log.logger.Logger

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

object AdbTerminal {

    private var device: Device? = null

    fun connect(logger: Logger = LoggerFactory.getDeviceLogger(LogLevel.INFO)) {
        if (device == null) device = Device.create(logger)
        device?.startConnectionToDesktop()
    }

    fun disconnect() {
        device?.stopConnectionToDesktop()
    }

    /**
     * Please first of all call [connect] method to establish a connection
     */
    fun executeAdb(command: String): CommandResult = device?.fulfill(
        AdbCommand(command)
    ) ?: throw IllegalStateException("Please first of all call [connect] method to establish a connection")

    /**
     * Allows more control over how arguments are parsed. Each element in the [arguments] list
     * is used as is without tokenizing.
     * Refer to the https://docs.oracle.com/javase/7/docs/api/java/lang/Runtime.html#exec(java.lang.String[])
     *
     * Please first of all call [connect] method to establish a connection
     */
    fun executeAdb(command: String, arguments: List<String>): CommandResult = device?.fulfill(
        ComplexAdbCommand(command, arguments)
    ) ?: throw IllegalStateException("Please first of all call [connect] method to establish a connection")

    /**
     * Please first of all call [connect] method to establish a connection
     */
    fun executeCmd(command: String): CommandResult = device?.fulfill(
        CmdCommand(command)
    ) ?: throw IllegalStateException("Please first of all call [connect] method to establish a connection")

    /**
     * Allows more control over how arguments are parsed. Each element in the [arguments] list
     * is used as is without tokenizing.
     * Refer to the https://docs.oracle.com/javase/7/docs/api/java/lang/Runtime.html#exec(java.lang.String[])
     *
     * Please first of all call [connect] method to establish a connection
     */
    fun executeCmd(command: String, arguments: List<String>): CommandResult = device?.fulfill(
        CmdCommand(command, arguments)
    ) ?: throw IllegalStateException("Please first of all call [connect] method to establish a connection")
}
