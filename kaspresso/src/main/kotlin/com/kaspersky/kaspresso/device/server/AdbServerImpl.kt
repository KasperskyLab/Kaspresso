package com.kaspersky.kaspresso.device.server

import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.test_server.AdbTerminal
import com.kaspersky.test_server.api.CommandResult
import com.kaspersky.test_server.api.ExecutorResultStatus

/**
 * Encapsulates all work with adb server.
 */
class AdbServerImpl(
    private val logger: UiTestLogger
) : AdbServer {

    private var initialized: Boolean = false

    private val adbTerminal: AdbTerminal
        get() {
            if (!initialized) AdbTerminal.connect()
            initialized = true
            return AdbTerminal
        }

    override fun performCmd(vararg commands: String): List<String> {
        return perform(commands) {
            adbTerminal.executeCmd(it)
        }
    }

    override fun performAdb(vararg commands: String): List<String> {
        return perform(commands) {
            adbTerminal.executeAdb(it)
        }
    }

    override fun performShell(vararg commands: String): List<String> {
        return perform(commands) {
            adbTerminal.executeAdb("shell $it")
        }
    }

    override fun disconnectServer() {
        if (initialized) {
            adbTerminal.disconnect()
            initialized = false
        }
    }

    private fun perform(commands: Array<out String>, executor: (String) -> CommandResult): List<String> {
        return commands.asSequence()
            .map { command -> command to executor.invoke(command) }
            .onEach { (command, result) ->
                logger.i("command=$command was performed with result=$result")
            }
            .onEach { (command, result) ->
                if (result.status == ExecutorResultStatus.FAILED)
                    throw AdbServerException("command=$command was performed with failed result=$result")
            }
            .map { (_, result) -> result.description }
            .toList()
    }
}