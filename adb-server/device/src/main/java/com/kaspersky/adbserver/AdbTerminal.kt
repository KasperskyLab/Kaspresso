package com.kaspersky.adbserver

import com.kaspersky.adbserver.api.CommandResult
import com.kaspresky.adbserver.log.LoggerFactory
import com.kaspresky.adbserver.log.fulllogger.LogPolicy
import com.kaspresky.adbserver.log.logger.Logger

object AdbTerminal {

    private var device: Device? = null

    fun connect(logger: Logger = LoggerFactory.getDeviceLogger(LogPolicy.DEBUG_FULL)) {
        if (device == null) device = Device.create(logger)
        device?.startConnectionToDesktop()
    }

    fun disconnect() {
        device?.stopConnectionToDesktop()
    }

    /**
     * Please first of all call [connect] method to establish a connection
     */
    fun executeAdb(command: String): CommandResult = device?.fulfill(
        AdbCommand(command)
    ) ?: throw IllegalStateException("Please first of all call [connect] method to establish a connection")

    /**
     * Please first of all call [connect] method to establish a connection
     */
    fun executeCmd(command: String): CommandResult = device?.fulfill(
        CmdCommand(command)
    ) ?: throw IllegalStateException("Please first of all call [connect] method to establish a connection")
}
