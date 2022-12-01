package com.kaspersky.adbserver.desktop

import com.kaspersky.adbserver.common.api.Command
import com.kaspersky.adbserver.common.api.CommandExecutor
import com.kaspersky.adbserver.common.api.CommandResult
import com.kaspersky.adbserver.commandtypes.AdbCommand
import com.kaspersky.adbserver.commandtypes.CmdCommand
import com.kaspersky.adbserver.common.log.logger.Logger
import java.lang.UnsupportedOperationException

internal class CommandExecutorImpl(
    private val cmdCommandPerformer: CmdCommandPerformer,
    private val adbCommandPerformer: AdbCommandPerformer,
    private val deviceName: String,
    private val adbServerPort: String?,
    private val logger: Logger
) : CommandExecutor {

    override fun execute(command: Command): CommandResult {
        return when (command) {
            is CmdCommand -> cmdCommandPerformer.perform(command.body)
            is AdbCommand -> {
                val adbCommand = "${ adbServerPort?.let { "-P $adbServerPort " } ?: "" }-s $deviceName ${command.body}"
                logger.d("The created adbCommand=adb $adbCommand")
                adbCommandPerformer.perform(adbCommand)
            }
            else -> throw UnsupportedOperationException("The command=$command is unsupported command")
        }
    }
}
