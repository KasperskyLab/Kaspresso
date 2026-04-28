package com.kaspersky.adbserver.desktop

import com.kaspersky.adbserver.common.api.CommandResult
import com.kaspersky.adbserver.common.api.ExecutorResultStatus
import com.kaspersky.adbserver.common.log.logger.Logger
import java.util.concurrent.TimeUnit

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

internal class CmdCommandPerformer(
    private val desktopName: String,
    private val logger: Logger,
    private val executionTimeoutSeconds: Long = 2 * 60L,
) {

    /**
     * Be aware it's a synchronous method
     */
    fun perform(command: String, arguments: List<String> = emptyList()): CommandResult {
        val serviceInfo = "The command was executed on desktop=$desktopName"

        val process = try {
            if (arguments.isEmpty()) {
                Runtime.getRuntime().exec(command)
            } else {
                val fullCommand = buildList {
                    add(command)
                    addAll(arguments)
                }.toTypedArray()
                Runtime.getRuntime().exec(fullCommand)
            }
        } catch (ex: Throwable) {
            logger.e(ex.stackTraceToString())
            return CommandResult(ExecutorResultStatus.FAILURE, "failed to start process. See exception in AdbServer logs")
        }

        try {
            if (process.waitFor(executionTimeoutSeconds, TimeUnit.SECONDS)) {
                val exitCode = process.exitValue()
                return if (exitCode != 0) {
                    val error = "exitCode=$exitCode, message=${process.errorStream.bufferedReader().readText()}"
                    CommandResult(
                        status = ExecutorResultStatus.FAILURE,
                        description = error,
                        serviceInfo = serviceInfo
                    )
                } else {
                    val success = "exitCode=$exitCode, message=${process.inputStream.bufferedReader().readText()}"
                    CommandResult(
                        status = ExecutorResultStatus.SUCCESS,
                        description = success,
                        serviceInfo = serviceInfo
                    )
                }
            }
            return CommandResult(
                status = ExecutorResultStatus.TIMEOUT,
                description = "Command execution timeout ($executionTimeoutSeconds sec) overhead",
                serviceInfo = serviceInfo
            )
        } finally {
            process?.destroy()
        }
    }
}
