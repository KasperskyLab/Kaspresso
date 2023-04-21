package com.kaspersky.adbserver.desktop

import com.kaspersky.adbserver.common.log.LoggerFactory
import com.kaspersky.adbserver.common.log.logger.LogLevel
import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import kotlinx.cli.delimiter
import java.lang.management.ManagementFactory

private const val DESKTOP = "Desktop-"
// It is assumed that adb is preinstall and available by "adb" keyword
private const val DEFAULT_ADB_PATH = "adb"

internal fun main(args: Array<String>) {
    val parser = ArgParser("Adb Server")

    val emulators by parser.option(
        type = ArgType.String,
        shortName = "e",
        fullName = "emulators",
        description = "List of emulators divided by commas"
    ).delimiter(",")

    val port by parser.option(
        type = ArgType.String,
        shortName = "p",
        fullName = "port",
        description = "Port to use"
    )

    val logLevel by parser.option(
        type = ArgType.Choice<LogLevel>(),
        shortName = "l",
        fullName = "logLevel",
        description = "Logs Level"
    ).default(LogLevel.INFO)

    val adbPath by parser.option(
        type = ArgType.String,
        shortName = "a",
        fullName = "adb_path",
        description = "Path to custom adb"
    ).default(DEFAULT_ADB_PATH)

    parser.parse(args)

    val desktopName = getDesktopName()
    val desktopLogger = LoggerFactory.getDesktopLogger(logLevel, desktopName)

    desktopLogger.i("Desktop started with arguments: emulators=$emulators, adbServerPort=$port, adbPath=$adbPath")

    val cmdCommandPerformer = CmdCommandPerformer(desktopName)
    val desktop = Desktop(
        cmdCommandPerformer = cmdCommandPerformer,
        presetEmulators = emulators,
        adbServerPort = port,
        logger = desktopLogger,
        adbPath = adbPath
    )
    desktop.startDevicesObserving()
}

private fun getDesktopName(): String {
    val processName = ManagementFactory.getRuntimeMXBean().name
    val pid = processName.split("@".toRegex()).toTypedArray()[0].toLong()
    return DESKTOP + pid
}
