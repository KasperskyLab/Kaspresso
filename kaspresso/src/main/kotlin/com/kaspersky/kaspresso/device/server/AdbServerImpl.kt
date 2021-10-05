package com.kaspersky.kaspresso.device.server

import com.kaspersky.adbserver.device.AdbTerminal
import com.kaspersky.adbserver.common.api.CommandResult
import com.kaspersky.adbserver.common.api.ExecutorResultStatus
import com.kaspersky.adbserver.common.log.logger.LogLevel
import com.kaspersky.adbserver.common.log.logger.Logger
import com.kaspersky.kaspresso.internal.exceptions.AdbServerException
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * The implementation of [AdbServer] interface. Encapsulates all work with adb server.
 * Please, pay attention to the field [AdbServerLogsType] that provides several types to show logs from adb-server (device part).
 * More details are available in [AdbServerLogsType].
 */
class AdbServerImpl(
    logLevel: LogLevel,
    private val logger: UiTestLogger
) : AdbServer {

    private val adbServerLogger: Logger = AdbServerLoggerKaspressoImpl(logLevel, logger)
    private var connected: Boolean = false

    private val adbTerminal: AdbTerminal
        get() {
            if (!connected) {
                AdbTerminal.connect(logger = adbServerLogger)
                connected = true
            }
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
        if (connected) {
            adbTerminal.disconnect()
            connected = false
        }
    }

    private fun perform(commands: Array<out String>, executor: (String) -> CommandResult): List<String> {
        return commands.asSequence()
            .onEach { command ->
                logger.i("AdbServer. The command to execute=$command")
            }
            .map { command -> command to executor.invoke(command) }
            .onEach { (command, result) ->
                logger.i("AdbServer. The command=$command was performed with result=$result")
            }
            .onEach { (command, result) ->
                if (result.status == ExecutorResultStatus.FAILURE) {
                    throw AdbServerException("AdbServer. The command=$command was performed with failed result=$result")
                }
                if (result.status == ExecutorResultStatus.TIMEOUT) {
                    throw AdbServerException(
                        """

                            AdbServer. The command=$command was performed with timeout exception.
                            There are two possible reasons:

                            1. The test is executing on the JVM (with Robolectric) environment and the test uses AdbServer. But, Unit tests can't use this implementation of AdbServer.
                            Possible solutions:
                            1. Rewrite the test and replace/remove a peace of code where AdbServer is called.
                            2. Write another implementation of AdbServer.
                            3. Don't use this test like a JVM(Unit)-test.

                            2. The second reason is absence of started 'adbserver-desktop.jar'.
                            Please, follow the instruction to resolve this issue:
                            1. Find the last 'adbserver-desktop.jar' here - https://github.com/KasperskyLab/Kaspresso/tree/master/artifacts.
                            2. Copy 'adbserver-desktop.jar' to your machine. For example, /Users/yuri.gagarin/Desktop/adbserver-desktop.jar.
                            3. Start 'adbserver-desktop.jar' with the command in Terminal - 'java -jar /Users/yuri.gagarin/Desktop/adbserver-desktop.jar

                        """.trimIndent()
                    )
                }
            }
            .map { (_, result) -> result.description }
            .toList()
    }
}
