package com.kaspersky.adbserver.desktop

import com.kaspersky.adbserver.common.api.Command
import com.kaspersky.adbserver.common.api.CommandExecutor
import com.kaspersky.adbserver.common.api.CommandResult
import com.kaspersky.adbserver.commandtypes.AdbCommand
import com.kaspersky.adbserver.commandtypes.CmdCommand
import com.kaspersky.adbserver.commandtypes.ComplexAdbCommand
import com.kaspersky.adbserver.commandtypes.ComplexCmdCommand
import com.kaspersky.adbserver.common.log.logger.Logger
import java.lang.UnsupportedOperationException

internal class CommandExecutorImpl(
    private val cmdCommandPerformer: CmdCommandPerformer,
    private val deviceName: String,
    private val adbServerPort: String?,
    private val logger: Logger,
    private val adbPath: String
) : CommandExecutor {

    override fun execute(command: Command): CommandResult {
        return when (command) {
            is CmdCommand -> cmdCommandPerformer.perform(command.command)
            is ComplexCmdCommand -> cmdCommandPerformer.perform(command.body)

            is AdbCommand -> {
                val adbCommand = "$adbPath ${adbServerPort?.let { "-P $adbServerPort " } ?: ""}-s $deviceName ${command.command}"
                logger.d("The created adbCommand=$adbCommand")
                cmdCommandPerformer.perform(adbCommand)
            }

            is ComplexAdbCommand -> {

                val adbCommand = buildList {
                    add(adbPath)
                    adbServerPort?.let {
                        add("-P")
                        add(adbServerPort)
                    }
                    add("-s")
                    add(deviceName)

                    addAll(command.body)
                }
                logger.d("The created complex adbCommand=$adbCommand")
                cmdCommandPerformer.perform(adbCommand)
            }

            else -> throw UnsupportedOperationException("The command=$command is unsupported command")
        }
    }
}
