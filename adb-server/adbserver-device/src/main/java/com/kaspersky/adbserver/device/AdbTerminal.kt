package com.kaspersky.adbserver.device

import com.kaspersky.adbserver.common.api.CommandResult
import com.kaspersky.adbserver.commandtypes.AdbCommand
import com.kaspersky.adbserver.commandtypes.CmdCommand
import com.kaspersky.adbserver.common.log.LoggerFactory
import com.kaspersky.adbserver.common.log.logger.LogLevel
import com.kaspersky.adbserver.common.log.logger.Logger

object AdbTerminal {

    private var device: Device? = null

    fun connect(logger: Logger = LoggerFactory.getDeviceLogger(LogLevel.INFO)) {
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
