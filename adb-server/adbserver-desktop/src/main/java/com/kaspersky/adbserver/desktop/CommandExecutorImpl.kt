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
    private val logger: Logger,
    private val adbPath: String
) : CommandExecutor {

    override fun execute(command: Command): CommandResult {
        return when (command) {
            is CmdCommand -> cmdCommandPerformer.perform(command.command, command.arguments)

            is AdbCommand -> {
                val adbCommand: String
                val adbArguments: List<String>

                if (command.arguments.isEmpty()) {
                    adbCommand = "${adbServerPort?.let { "-P $adbServerPort " } ?: ""}-s $deviceName ${command.command}"
                    adbArguments = emptyList()
                } else {
                    adbCommand = adbPath
                    adbArguments = buildList {
                        adbServerPort?.let {
                            add("-P")
                            add(adbServerPort)
                        }
                        add("-s")
                        add(deviceName)
                        add(command.command)
                        addAll(command.arguments)
                    }
                }
                logger.d("The created adbCommand=$adbCommand, arguments=$adbArguments")
                cmdCommandPerformer.perform(adbCommand, adbArguments)
            }

            else -> throw UnsupportedOperationException("The command=$command is unsupported command")
        }
    }
}
