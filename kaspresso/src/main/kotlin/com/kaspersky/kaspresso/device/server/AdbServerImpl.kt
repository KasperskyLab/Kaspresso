package com.kaspersky.kaspresso.device.server

import com.kaspersky.test_server_mobile.HostConnection

/**
 * An implementation of AdbServer interface.
 */
object AdbServerImpl : AdbServer {

    private val hostConnection: HostConnection by lazy {
        HostConnection.apply {
            start()
        }
    }

    /**
     *  Executes shell commands blocking current thread.
     */
    override fun performCmd(vararg commands: String) {
        performCommand(commands) { executeCmdCommand(it) }
    }

    /**
     *  Performs adb commands blocking current thread.
     */
    override fun performAdb(vararg commands: String) {
        performCommand(commands) { executeAdbCommand(it) }
    }

    private fun performCommand(commands: Array<out String>, executor: HostConnection.(String) -> Unit) {
        commands.forEach { cmd ->
            hostConnection.runCatching {
                executor(cmd)
            }
        }
    }
}