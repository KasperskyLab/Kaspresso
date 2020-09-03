package com.kaspersky.adbserver.api

interface ConnectionServerLifecycle {

    fun onReceivedTask(command: Command)
    fun onExecutedTask(command: Command, commandResult: CommandResult)
    fun onDisconnectedBySocketProblems()

}