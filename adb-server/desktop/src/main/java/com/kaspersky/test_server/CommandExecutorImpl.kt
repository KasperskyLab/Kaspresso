package com.kaspersky.test_server

import com.kaspersky.test_server.api.Command
import com.kaspersky.test_server.api.CommandExecutor
import com.kaspersky.test_server.api.CommandResult
import com.kaspresky.test_server.log.LoggerFactory
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