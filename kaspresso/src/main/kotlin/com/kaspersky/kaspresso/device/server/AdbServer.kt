package com.kaspersky.kaspresso.device.server

import com.kaspersky.test_server.AdbTerminal
import com.kaspersky.test_server.api.CommandResult
import com.kaspersky.test_server.api.ExecutorResultStatus

/**
 * Encapsulates all work with adb server.
 */
object AdbServer {

    private val adbTerminal: AdbTerminal by lazy {
        AdbTerminal.apply {
            connect()
        }
    }

    /**
     *  Executes shell commands blocking current thread.
     *  Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown
     *
     *  Required Permissions: INTERNET.
     *
     *  @param commands commands to execute.
     *  @throws AdbServerException if a result status of any command in @param commands is Failed
     *  @return list of answers of commands' execution
     */
    fun performCmd(vararg commands: String): List<String> {
        return perform(commands) {
            adbTerminal.executeCmd(it)
        }
    }

    /**
     *  Performs adb commands blocking current thread.
     *  Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown
     *
     *  Required Permissions: INTERNET.
     *
     *  @param commands commands to execute.
     *  @throws AdbServerException if a result status of any command in @param commands is Failed
     *  @return list of answers of commands' execution
     */
    fun performAdb(vararg commands: String): List<String> {
        return perform(commands) {
            adbTerminal.executeAdb(it)
        }
    }

    /**
     *  Performs shell commands blocking current thread.
     *  Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown
     *
     *  Required Permissions: INTERNET.
     *
     *  @param commands commands to execute.
     *  @throws AdbServerException if a result status of any command in @param commands is Failed
     *  @return list of answers of commands' execution
     */
    fun performShell(vararg commands: String): List<String> {
        return perform(commands) {
            adbTerminal.executeAdb("shell $it")
        }
    }

    private fun perform(commands: Array<out String>, executor: (String) -> CommandResult): List<String> {
        return commands.asSequence()
            .map { command -> command to executor.invoke(command) }
            .onEach { (command, result) ->
                // TODO
//                Configurator.logger.i("command=$command was performed with result=$result")
            }
            .onEach { (command, result) ->
                if (result.status == ExecutorResultStatus.FAILED)
                    throw AdbServerException("command=$command was performed with failed result=$result")
            }
            .map { (_, result) -> result.description }
            .toList()
    }
}