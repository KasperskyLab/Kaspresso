package com.kaspersky.adbserver

import com.kaspersky.adbserver.api.CommandResult

object AdbTerminal {

    private val device = Device.create()

    fun connect() {
        device.startConnectionToDesktop()
    }

    fun disconnect() {
        device.stopConnectionToDesktop()
    }

    /**
     * Please first of all call [connect] method to establish a connection
     */
    fun executeAdb(command: String): CommandResult = device.fulfill(
        AdbCommand(command)
    )

    /**
     * Please first of all call [connect] method to establish a connection
     */
    fun executeCmd(command: String): CommandResult = device.fulfill(
        CmdCommand(command)
    )
}
