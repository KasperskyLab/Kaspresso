package com.kaspersky.adbserver.connection.api

import com.kaspersky.adbserver.common.api.Command
import com.kaspersky.adbserver.common.api.CommandResult

interface ConnectionServerLifecycle {

    fun onReceivedTask(command: Command)
    fun onExecutedTask(command: Command, commandResult: CommandResult)
    fun onDisconnectedBySocketProblems()
}
