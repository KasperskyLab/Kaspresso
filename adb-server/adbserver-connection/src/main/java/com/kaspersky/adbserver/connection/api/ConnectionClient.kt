package com.kaspersky.adbserver.connection.api

import com.kaspersky.adbserver.common.api.Command
import com.kaspersky.adbserver.common.api.CommandResult

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
