package com.kaspersky.adbserver.desktop

import com.kaspersky.adbserver.common.log.LoggerFactory
import com.kaspersky.adbserver.common.log.logger.LogLevel
import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import kotlinx.cli.delimiter
import java.lang.management.ManagementFactory
import java.nio.file.Path

private const val DESKTOP = "Desktop-"

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
        fullName = "adbPath",
        description = "Path to adb binary, if not set when use 'adb' from process environment"
    )

    parser.parse(args)

    val desktopName = getDesktopName()
    val desktopLogger = LoggerFactory.getDesktopLogger(logLevel, desktopName)

    desktopLogger.i("Desktop started with arguments: emulators=$emulators, adbServerPort=$port")

    val cmdCommandPerformer = CmdCommandPerformer(desktopName)
    val adbCommandPerformer = AdbCommandPerformer(adbPath?.let { Path.of(it) }, cmdCommandPerformer)
    val desktop = Desktop(
        cmdCommandPerformer = cmdCommandPerformer,
        adbCommandPerformer = adbCommandPerformer,
        presetEmulators = emulators,
        adbServerPort = port,
        logger = desktopLogger
    )
    desktop.startDevicesObservingSync()
}

private fun getDesktopName(): String {
    val processName = ManagementFactory.getRuntimeMXBean().name
    val pid = processName.split("@".toRegex()).toTypedArray()[0].toLong()
    return DESKTOP + pid
}
