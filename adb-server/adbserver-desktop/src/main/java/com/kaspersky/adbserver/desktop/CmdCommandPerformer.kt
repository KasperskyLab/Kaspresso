package com.kaspersky.adbserver.desktop

import com.kaspersky.adbserver.common.api.CommandResult
import com.kaspersky.adbserver.common.api.ExecutorResultStatus
import com.kaspersky.adbserver.common.log.logger.Logger
import java.io.IOException
import java.io.InputStream
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

@Suppress("MagicNumber")
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
            val fullCommand = buildList {
                add(command)
                addAll(arguments)
            }.toTypedArray()

            Runtime.getRuntime().exec(fullCommand)
        } catch (ex: Throwable) {
            logger.e(ex.stackTraceToString())
            return CommandResult(ExecutorResultStatus.FAILURE, "failed to start process. See exception in AdbServer logs")
        }

        // stdout/stderr must be consumed concurrently with waitFor: a process that prints more
        // than the OS pipe buffer holds blocks on write and never exits if nobody reads the pipe
        val stdoutReader = StreamGobbler(process.inputStream)
        val stderrReader = StreamGobbler(process.errorStream)

        try {
            if (process.waitFor(executionTimeoutSeconds, TimeUnit.SECONDS)) {
                val exitCode = process.exitValue()
                return if (exitCode != 0) {
                    val error = "exitCode=$exitCode, message=${stderrReader.awaitText()}"
                    CommandResult(
                        status = ExecutorResultStatus.FAILURE,
                        description = error,
                        serviceInfo = serviceInfo
                    )
                } else {
                    val success = "exitCode=$exitCode, message=${stdoutReader.awaitText()}"
                    CommandResult(
                        status = ExecutorResultStatus.SUCCESS,
                        description = success,
                        serviceInfo = serviceInfo
                    )
                }
            }
            return CommandResult(
                status = ExecutorResultStatus.TIMEOUT,
                description = "Command execution timeout ($executionTimeoutSeconds sec) overhead. " +
                        "The output captured before the timeout (may be partial): " +
                        "stdout=${stdoutReader.textSnapshot().takeLast(CAPTURED_OUTPUT_TAIL_LENGTH)}, " +
                        "stderr=${stderrReader.textSnapshot().takeLast(CAPTURED_OUTPUT_TAIL_LENGTH)}",
                serviceInfo = serviceInfo
            )
        } finally {
            process.destroy()
        }
    }

    private companion object {
        private const val CAPTURED_OUTPUT_TAIL_LENGTH = 2_000
    }

    /**
     * Reads the given process stream on a background thread while the process is running.
     *
     * Consuming stdout/stderr concurrently with `Process.waitFor` is mandatory: a process that
     * prints more than the OS pipe buffer holds blocks on write and never exits otherwise.
     * The captured text is capped by [MAX_CAPTURED_LENGTH] to keep a runaway command from
     * exhausting the JVM heap; the stream is still drained to the end after the cap is reached.
     */
    private class StreamGobbler(private val stream: InputStream) {

        private val text = StringBuffer()

        private val thread = Thread {
            try {
                val reader = stream.bufferedReader()
                val chunk = CharArray(CHUNK_SIZE)
                while (true) {
                    val readCount = reader.read(chunk)
                    if (readCount < 0) break
                    // the capture is capped so that a runaway command cannot exhaust the JVM heap,
                    // but the stream must be drained further anyway to let the process finish
                    if (text.length < MAX_CAPTURED_LENGTH) {
                        val allowedCount = minOf(readCount, MAX_CAPTURED_LENGTH - text.length)
                        text.append(chunk, 0, allowedCount)
                        if (text.length == MAX_CAPTURED_LENGTH) {
                            text.append(TRUNCATION_MARKER)
                        }
                    }
                }
            } catch (_: IOException) {
                // the stream is closed when the process is destroyed after a timeout
            }
        }.apply {
            isDaemon = true
            start()
        }

        fun awaitText(): String {
            thread.join(READER_JOIN_TIMEOUT_MS)
            return text.toString()
        }

        fun textSnapshot(): String = text.toString()

        private companion object {
            private const val READER_JOIN_TIMEOUT_MS = 5_000L
            private const val CHUNK_SIZE = 8 * 1024
            private const val MAX_CAPTURED_LENGTH = 5 * 1024 * 1024
            private const val TRUNCATION_MARKER = "... [the rest of the output is truncated]"
        }
    }
}
