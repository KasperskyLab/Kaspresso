package com.kaspersky.kaspresso.device.server

import com.kaspersky.test_server_mobile.HostConnection

/**
 * Encapsulates all work with adb server.
 */
object AdbServer {

    private val hostConnection: HostConnection by lazy {
        HostConnection.apply {
            start()
        }
    }

    /**
     *  Executes shell commands blocking current thread.
     *
     *  @param commands commands to execute.
     */
    fun performCmd(vararg commands: String) {
        performCommand(commands) { executeCmdCommand(it) }
    }

    /**
     *  Performs adb commands blocking current thread.
     *
     *  @param commands commands to execute.
     */
    fun performAdb(vararg commands: String) {
        performCommand(commands) { executeAdbCommand(it) }
    }

    /**
     *  Performs shell commands blocking current thread.
     *
     *  @param commands commands to execute.
     */
    fun performShell(vararg commands: String) {
        performCommand(commands) { executeAdbCommand("shell $it") }
    }

    private fun performCommand(commands: Array<out String>, executor: HostConnection.(String) -> Unit) {
        commands.forEach { cmd ->
            hostConnection.runCatching {
                executor(cmd)
            }
        }
    }
}