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

    @Deprecated("This method doesn't work for the commands with the complex arguments containing " +
            "whitespaces (e.g. 'adb pull \"path/with whitespace/file\") and doesn't allow commands piping like" +
            "adbServer.performCmd(\"bash\", listOf(\"-c\", \"adb shell dumpsys deviceidle | grep mForceIdle\"))" +
            "which the AdbServer.performCmd(String, List<String>) does. " +
            "For more details, please check out https://github.com/KasperskyLab/Kaspresso/blob/master/docs/Wiki/Executing_adb_commands.en.md",
        ReplaceWith("adbServer.performCmd(*commands, emptyList())")
    )
    override fun performCmd(vararg commands: String): List<String> {
        return commands.map { command ->
            perform(command, emptyList()) { command, _ ->
                adbTerminal.executeCmd(command)
            }
        }
    }

    @Deprecated("This method doesn't work for the commands with the complex arguments containing " +
            "whitespaces (e.g. 'adb pull \"path/with whitespace/file\") and doesn't allow commands piping like" +
            "adbServer.performCmd(\"bash\", listOf(\"-c\", \"adb shell dumpsys deviceidle | grep mForceIdle\"))" +
            "which the AdbServer.performCmd(String, List<String>) does " +
            "For more details, please check out https://github.com/KasperskyLab/Kaspresso/blob/master/docs/Wiki/Executing_adb_commands.en.md",
        ReplaceWith("adbServer.performAdb(*commands, emptyList())")
    )
    override fun performAdb(vararg commands: String): List<String> {
        return commands.map { command ->
            perform(command, emptyList()) { command, _ ->
                adbTerminal.executeAdb(command)
            }
        }
    }

    @Deprecated("This method doesn't work for the commands with the complex arguments containing " +
            "whitespaces (e.g. 'adb pull \"path/with whitespace/file\") and doesn't allow commands piping like" +
            "adbServer.performCmd(\"bash\", listOf(\"-c\", \"adb shell dumpsys deviceidle | grep mForceIdle\"))" +
            "which the AdbServer.performCmd(String, List<String>) does " +
            "For more details, please check out https://github.com/KasperskyLab/Kaspresso/blob/master/docs/Wiki/Executing_adb_commands.en.md",
        ReplaceWith("adbServer.performShell(*commands, emptyList())")
    )
    override fun performShell(vararg commands: String): List<String> {
        return commands.map { command ->
            perform(command, emptyList()) { command, _ ->
                adbTerminal.executeAdb("shell $command")
            }
        }
    }

    override fun performCmd(command: String, arguments: List<String>): String {
        return perform(command, arguments, adbTerminal::executeCmd)
    }

    override fun performAdb(command: String, arguments: List<String>): String {
        return perform(command, arguments, adbTerminal::executeAdb)
    }

    override fun performShell(command: String, arguments: List<String>): String {
        val args = buildList {
            add(command)
            addAll(arguments)
        }
        return perform("shell", args, adbTerminal::executeAdb)
    }

    override fun disconnectServer() {
        if (connected) {
            adbTerminal.disconnect()
            connected = false
        }
    }

    private fun perform(command: String, arguments: List<String>, executor: (String, List<String>) -> CommandResult): String {
        logger.i("AdbServer. The command to execute=$command, arguments=$arguments")
        val result = executor.invoke(command, arguments)
        logCommandResult(command, result)

        return result.description
    }

    private fun logCommandResult(command: String, result: CommandResult, arguments: List<String> = emptyList()) {
        logger.i("AdbServer. The command=$command with arguments=$arguments was performed with result=$result")
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
                        a. Rewrite the test and replace/remove a peace of code where AdbServer is called.
                        b. Write another implementation of AdbServer.
                        c. Don't use this test like a JVM(Unit)-test.

                    2. The second reason is absence of started 'adbserver-desktop.jar'.
                    Please, follow the instruction to resolve this issue:
                        a. Find the last 'adbserver-desktop.jar' here - https://github.com/KasperskyLab/Kaspresso/tree/master/artifacts.
                        b. Copy 'adbserver-desktop.jar' to your machine. For example, /Users/yuri.gagarin/Desktop/adbserver-desktop.jar.
                        c. Start 'adbserver-desktop.jar' with the command in Terminal - 'java -jar /Users/yuri.gagarin/Desktop/adbserver-desktop.jar

                        """.trimIndent()
            )
        }
    }
}
