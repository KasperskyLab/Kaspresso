package com.kaspersky.kaspresso.device.server

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.test_server.AdbTerminal
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
        return commands.asSequence()
            .map { command -> command to adbTerminal.executeCmd(command) }
            .onEach { (command, result) ->
                Configurator.logger.i("cmd command=$command was performed with result=$result")
            }
            .onEach { (command, result) ->
                if (result.status == ExecutorResultStatus.FAILED)
                    throw AdbServerException("cmd command=$command was performed with failed result=$result")
            }
            .map { (_, result) -> result.description }
            .toList()
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
        return commands.asSequence()
            .map { command -> command to adbTerminal.executeAdb(command) }
            .onEach { (command, result) ->
                Configurator.logger.i("adb command=$command was performed with result=$result")
            }
            .onEach { (command, result) ->
                if (result.status == ExecutorResultStatus.FAILED)
                    throw AdbServerException("adb command=$command was performed with failed result=$result")
            }
            .map { (_, result) -> result.description }
            .toList()
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
        return commands.asSequence()
            .onEach { command -> performAdb("shell $command") }
            .toList()
    }
}