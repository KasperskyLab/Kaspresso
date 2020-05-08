package com.kaspersky.testserver

import com.kaspersky.testserver.api.Command
import com.kaspersky.testserver.api.CommandExecutor
import com.kaspersky.testserver.api.CommandResult
import com.kaspresky.testserver.log.LoggerFactory
import java.lang.UnsupportedOperationException

internal class CommandExecutorImpl(
    private val cmdCommandPerformer: CmdCommandPerformer,
    private val deviceName: String,
    private val adbServerPort: String?
) : CommandExecutor {

    private val logger = LoggerFactory.getLogger(tag = javaClass.simpleName, deviceName = deviceName)

    override fun execute(command: Command): CommandResult {
        return when (command) {
            is CmdCommand -> cmdCommandPerformer.perform(command.body)
            is AdbCommand -> {
                val adbCommand = "adb ${ adbServerPort?.let { "-P $adbServerPort " } ?: "" }-s $deviceName ${command.body}"
                logger.i("execute", "adbCommand=$adbCommand")
                cmdCommandPerformer.perform(adbCommand)
            }
            else -> throw UnsupportedOperationException("The command=$command is unsupported command")
        }
    }
}
