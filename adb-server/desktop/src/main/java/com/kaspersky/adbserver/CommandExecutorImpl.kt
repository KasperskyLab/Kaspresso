package com.kaspersky.adbserver

import com.kaspersky.adbserver.api.Command
import com.kaspersky.adbserver.api.CommandExecutor
import com.kaspersky.adbserver.api.CommandResult
import com.kaspresky.adbserver.log.logger.Logger
import java.lang.UnsupportedOperationException

internal class CommandExecutorImpl(
    private val cmdCommandPerformer: CmdCommandPerformer,
    private val deviceName: String,
    private val adbServerPort: String?,
    private val logger: Logger
) : CommandExecutor {

    override fun execute(command: Command): CommandResult {
        return when (command) {
            is CmdCommand -> cmdCommandPerformer.perform(command.body)
            is AdbCommand -> {
                val adbCommand = "adb ${ adbServerPort?.let { "-P $adbServerPort " } ?: "" }-s $deviceName ${command.body}"
                logger.d("The created adbCommand=$adbCommand")
                cmdCommandPerformer.perform(adbCommand)
            }
            else -> throw UnsupportedOperationException("The command=$command is unsupported command")
        }
    }
}
