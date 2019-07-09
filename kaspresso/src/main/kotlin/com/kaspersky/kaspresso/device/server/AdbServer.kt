package com.kaspersky.kaspresso.device.server

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.test_server.AdbTerminal
import com.kaspersky.test_server.api.ExecutorResultStatus

/**
 * Encapsulates all work with adb server.
 */
object AdbServer {

    private val TAG = javaClass.simpleName

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
     */
    fun performCmd(vararg commands: String) {
        commands.forEach { command ->
            val commandResult = adbTerminal.executeCmd(command)
            Configurator.logger.i(TAG, "cmd command=$command was performed with result=$commandResult")
            if (commandResult.status == ExecutorResultStatus.FAILED) {
                throw AdbServerException("cmd command=$command was performed with failed result=$commandResult")
            }
        }
    }

    /**
     *  Performs adb commands blocking current thread.
     *
     *  Required Permissions: INTERNET.
     *
     *  @param commands commands to execute.
     *  @throws AdbServerException if command result status is Failed
     */
    fun performAdb(vararg commands: String) {
        commands.forEach { command ->
            val commandResult = adbTerminal.executeAdb(command)
            Configurator.logger.i(TAG, "adb command=$command was performed with result=$commandResult")
            if (commandResult.status == ExecutorResultStatus.FAILED) {
                throw AdbServerException("adb command=$command was performed with failed result=$commandResult")
            }
        }
    }

    /**
     *  Performs shell commands blocking current thread.
     *
     *  Required Permissions: INTERNET.
     *
     *  @param commands commands to execute.
     */
    fun performShell(vararg commands: String) {
        commands.forEach { command ->
            performAdb("shell $command")
        }
    }
}