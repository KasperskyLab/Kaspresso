package com.kaspersky.adbserver

import com.kaspersky.adbserver.api.CommandResult
import com.kaspersky.adbserver.api.ExecutorResultStatus
import java.util.concurrent.TimeUnit

internal class CmdCommandPerformer {

    companion object {
        private const val EXECUTION_TIMEOUT_SECONDS = 2 * 60L
    }

    /**
     * Be aware it's a synchronous method
     */
    fun perform(command: String): CommandResult {
        val process = Runtime.getRuntime().exec(command)
        try {
            if (process.waitFor(EXECUTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
                val exitCode = process.exitValue()
                return if (exitCode != 0) {
                    val error = "exitCode=$exitCode, message=${process.errorStream.bufferedReader().readText()}"
                    CommandResult(
                        ExecutorResultStatus.FAILED,
                        error
                    )
                } else {
                    val success = "exitCode=$exitCode, message=${process.inputStream.bufferedReader().readText()}"
                    CommandResult(
                        ExecutorResultStatus.SUCCESS,
                        success
                    )
                }
            }
            return CommandResult(
                ExecutorResultStatus.FAILED,
                "Command execution timeout ($EXECUTION_TIMEOUT_SECONDS sec) overhead"
            )
        } finally {
            process.destroy()
        }
    }
}
