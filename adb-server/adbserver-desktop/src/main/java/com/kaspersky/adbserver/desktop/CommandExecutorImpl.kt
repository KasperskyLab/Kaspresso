package com.kaspersky.adbserver.desktop

import com.kaspersky.adbserver.common.api.Command
import com.kaspersky.adbserver.common.api.CommandExecutor
import com.kaspersky.adbserver.common.api.CommandResult
import com.kaspersky.adbserver.commandtypes.AdbCommand
import com.kaspersky.adbserver.commandtypes.CmdCommand
import com.kaspersky.adbserver.commandtypes.ComplexAdbCommand
import com.kaspersky.adbserver.common.log.logger.Logger
import java.lang.UnsupportedOperationException

internal class CommandExecutorImpl(
    private val cmdCommandPerformer: CmdCommandPerformer,
    private val deviceName: String,
    private val adbServerPort: String?,
    private val logger: Logger,
    private val adbPath: String
) : CommandExecutor {

    private fun getSimpleAdbCommand(command: Command): String = "$adbPath ${adbServerPort?.let { "-P $adbServerPort " } ?: ""}-s $deviceName ${command.body}"

    override fun execute(command: Command): CommandResult {
        return when (command) {
            is CmdCommand -> cmdCommandPerformer.perform(command.body, command.arguments)

            is AdbCommand -> {
                val adbCommand = getSimpleAdbCommand(command)
                logger.d("The created adbCommand=$adbCommand")
                cmdCommandPerformer.perform(adbCommand, emptyList())
            }

            is ComplexAdbCommand -> {
                val adbCommand: String
                val adbArguments: List<String>

                if (command.arguments.isEmpty()) {
                    adbCommand = getSimpleAdbCommand(command)
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
                        add(command.body)
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
