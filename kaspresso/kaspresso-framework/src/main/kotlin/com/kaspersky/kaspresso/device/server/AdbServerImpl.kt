package com.kaspersky.kaspresso.device.server

import com.kaspersky.adbserver.device.AdbTerminal
import com.kaspersky.adbserver.common.api.CommandResult
import com.kaspersky.adbserver.common.api.ExecutorResultStatus
import com.kaspersky.kaspresso.internal.exceptions.AdbServerException
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * The implementation of [AdbServer] interface. Encapsulates all work with adb server.
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

    /**
     * Executes shell commands blocking current thread.
     * Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown
     *
     * Required Permissions: INTERNET.
     *
     * @param commands commands to execute.
     * @throws AdbServerException if a result status of any command in @param commands is Failed
     * @return list of answers of commands' execution
     */
    override fun performCmd(vararg commands: String): List<String> {
        return perform(commands) {
            adbTerminal.executeCmd(it)
        }
    }

    /**
     * Performs adb commands blocking current thread.
     * Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown
     *
     * Required Permissions: INTERNET.
     *
     * @param commands commands to execute.
     * @throws AdbServerException if a result status of any command in @param commands is Failed
     * @return list of answers of commands' execution
     */
    override fun performAdb(vararg commands: String): List<String> {
        return perform(commands) {
            adbTerminal.executeAdb(it)
        }
    }

    /**
     * Performs shell commands blocking current thread.
     * Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown
     *
     * Required Permissions: INTERNET.
     *
     * @param commands commands to execute.
     * @throws AdbServerException if a result status of any command in @param commands is Failed
     * @return list of answers of commands' execution
     */
    override fun performShell(vararg commands: String): List<String> {
        return perform(commands) {
            adbTerminal.executeAdb("shell $it")
        }
    }

    /**
     * Disconnect from AdbServer.
     * The method is called by Kaspresso after each test.
     */
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
                logger.i("Command=$command was performed with result=$result")
            }
            .onEach { (command, result) ->
                if (result.status == ExecutorResultStatus.FAILURE) {
                    throw AdbServerException("Command=$command was performed with failed result=$result")
                }
                if (result.status == ExecutorResultStatus.TIMEOUT) {
                    throw AdbServerException(
                        "Command=$command was performed with timeout exception. \n" +
                                "The possible reason (99.9%) is absence of started 'desktop.jar' (AdbServer). \n" +
                                "Please, follow the instruction: \n" +
                                "1. Find the last 'desktop.jar' here - https://github.com/KasperskyLab/Kaspresso/tree/master/artifacts \n" +
                                "2. Copy 'desktop.jar' to your machine. For example, /Users/yuri.gagarin/Desktop/desktop.jar. \n" +
                                "3. Start 'desktop.jar' with the command in Terminal - 'java -jar /Users/yuri.gagarin/Desktop/desktop.jar"
                    )
                }
            }
            .map { (_, result) -> result.description }
            .toList()
    }
}
