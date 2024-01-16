package com.kaspersky.adbserver.desktop

import com.kaspersky.adbserver.common.api.CommandResult
import com.kaspersky.adbserver.common.api.ExecutorResultStatus
import com.kaspersky.adbserver.common.log.logger.Logger
import java.util.concurrent.TimeUnit

internal class CmdCommandPerformer(
    private val desktopName: String,
    private val logger: Logger,
) {

    companion object {
        private const val EXECUTION_TIMEOUT_SECONDS = 2 * 60L
    }

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
            if (process.waitFor(EXECUTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
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
                description = "Command execution timeout ($EXECUTION_TIMEOUT_SECONDS sec) overhead",
                serviceInfo = serviceInfo
            )
        } finally {
            process?.destroy()
        }
    }
}
