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
     *
     *  Required Permissions: INTERNET.
     *
     *  @param commands commands to execute.
     *  @throws AdbServerException if command result status is Failed
     *  @return list of answers of command execution
     */
    fun performCmd(vararg commands: String): List<String> {
        val answers = mutableListOf<String>()
        commands.forEach { command ->
            val commandResult = adbTerminal.executeCmd(command)
            Configurator.logger.i("cmd command=$command was performed with result=$commandResult")
            if (commandResult.status == ExecutorResultStatus.FAILED) {
                throw AdbServerException("cmd command=$command was performed with failed result=$commandResult")
            }
            answers.add(commandResult.description)
        }
        return answers
    }

    /**
     *  Performs adb commands blocking current thread.
     *
     *  Required Permissions: INTERNET.
     *
     *  @param commands commands to execute.
     *  @throws AdbServerException if command result status is Failed
     *  @return list of answers of command execution
     */
    fun performAdb(vararg commands: String): List<String> {
        val answers = mutableListOf<String>()
        commands.forEach { command ->
            val commandResult = adbTerminal.executeAdb(command)
            Configurator.logger.i("adb command=$command was performed with result=$commandResult")
            if (commandResult.status == ExecutorResultStatus.FAILED) {
                throw AdbServerException("adb command=$command was performed with failed result=$commandResult")
            }
            answers.add(commandResult.description)
        }
        return answers
    }

    /**
     *  Performs shell commands blocking current thread.
     *
     *  Required Permissions: INTERNET.
     *
     *  @param commands commands to execute.
     *  @throws AdbServerException if command result status is Failed
     *  @return list of answers of command execution
     */
    fun performShell(vararg commands: String): List<String> {
        val answers = mutableListOf<String>()
        commands.forEach { command ->
            answers.addAll(
                performAdb("shell $command")
            )
        }
        return answers
    }
}