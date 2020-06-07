package com.kaspersky.adbserver.api

/**
 * Executor of terminal commands.
 */
interface CommandExecutor {

    /**
     * It's a synchronous method to keep order of commands' executing.
     * Otherwise the result may be unpredictable.
     */
    fun execute(command: Command): CommandResult
}
