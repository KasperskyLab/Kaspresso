package com.kaspersky.uitest_framework.server

import com.kaspersky.test_server_mobile.HostConnection

object DefaultAdbServer : ServerInterface {

    private val hostConnection: HostConnection by lazy {
        HostConnection.apply {
            start()
        }
    }

    override fun performCmd(vararg commands: String) {
        performCommand(commands) { executeCmdCommand(it) }
    }

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
