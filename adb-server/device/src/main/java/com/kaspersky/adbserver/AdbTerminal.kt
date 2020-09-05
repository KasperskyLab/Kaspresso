package com.kaspersky.adbserver

import com.kaspersky.adbserver.api.CommandResult
import com.kaspresky.adbserver.log.LoggerFactory
import com.kaspresky.adbserver.log.fulllogger.LogPolicy

object AdbTerminal {

    private val device = Device.create(LoggerFactory.getDeviceLogger(LogPolicy.INFO))

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
