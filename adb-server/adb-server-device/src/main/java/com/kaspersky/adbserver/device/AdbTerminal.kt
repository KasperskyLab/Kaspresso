package com.kaspersky.adbserver.device

import com.kaspersky.adbserver.common.api.CommandResult
import com.kaspersky.adbserver.commandtypes.AdbCommand
import com.kaspersky.adbserver.commandtypes.CmdCommand
import com.kaspersky.adbserver.commandtypes.ComplexAdbCommand
import com.kaspersky.adbserver.commandtypes.ComplexCmdCommand
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
     * Allows more control over how arguments are parsed. Each list element is a command or an argument used as is.
     * Refer to the https://docs.oracle.com/javase/8/docs/api/java/lang/ProcessBuilder.html
     *
     * @param command the list of the commands and arguments
     * Please first of all call [connect] method to establish a connection
     */
    fun executeAdb(command: List<String>): CommandResult = device?.fulfill(
        ComplexAdbCommand(command)
    ) ?: throw IllegalStateException("Please first of all call [connect] method to establish a connection")

    /**
     * Please first of all call [connect] method to establish a connection
     */
    fun executeCmd(command: String): CommandResult = device?.fulfill(
        CmdCommand(command)
    ) ?: throw IllegalStateException("Please first of all call [connect] method to establish a connection")

    /**
     * Allows more control over how arguments are parsed. Each list element is a command or an argument used as is.
     * Refer to the https://docs.oracle.com/javase/8/docs/api/java/lang/ProcessBuilder.html
     *
     * @param command the list of the commands and arguments
     * Please first of all call [connect] method to establish a connection
     */
    fun executeCmd(command: List<String>): CommandResult = device?.fulfill(
        ComplexCmdCommand(command)
    ) ?: throw IllegalStateException("Please first of all call [connect] method to establish a connection")
}
