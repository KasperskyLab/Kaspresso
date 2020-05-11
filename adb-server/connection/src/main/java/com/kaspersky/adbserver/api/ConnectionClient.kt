package com.kaspersky.adbserver.api

/**
 * BaseConnection + opportunity to execute Adb commands
 */
interface ConnectionClient : BaseConnection {

    /**
     * It is synchronous method to not reorder a line of commands
     * because if commands were completed in incorrect order it may to lead inconsistent state of the app and the device
     */
    fun executeCommand(command: Command): CommandResult
}
