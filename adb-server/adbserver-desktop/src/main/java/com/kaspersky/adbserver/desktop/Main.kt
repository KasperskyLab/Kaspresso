package com.kaspersky.adbserver.desktop

import com.kaspersky.adbserver.common.log.LoggerFactory
import com.kaspersky.adbserver.common.log.fulllogger.LogPolicy
import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import kotlinx.cli.delimiter
import java.lang.management.ManagementFactory

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

    val logPolicy by parser.option(
        type = ArgType.Choice<LogPolicy>(),
        shortName = "l",
        fullName = "logs",
        description = "Logs policy"
    ).default(LogPolicy.INFO)

    parser.parse(args)

    val desktopName = getDesktopName()
    val desktopLogger = LoggerFactory.getDesktopLogger(logPolicy, desktopName)

    desktopLogger.i("Desktop started with arguments: emulators=$emulators, adbServerPort=$port")

    val cmdCommandPerformer = CmdCommandPerformer(desktopName)
    val desktop = Desktop(
        cmdCommandPerformer = cmdCommandPerformer,
        presetEmulators = emulators,
        adbServerPort = port,
        logger = desktopLogger
    )
    desktop.startDevicesObserving()
}

private fun getDesktopName(): String {
    val processName = ManagementFactory.getRuntimeMXBean().name
    val pid = processName.split("@".toRegex()).toTypedArray()[0].toLong()
    return DESKTOP + pid
}
