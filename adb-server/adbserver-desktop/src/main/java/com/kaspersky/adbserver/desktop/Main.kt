package com.kaspersky.adbserver.desktop

import com.kaspersky.adbserver.common.log.LoggerFactory
import com.kaspersky.adbserver.common.log.logger.LogLevel
import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import kotlinx.cli.delimiter
import java.io.File
import java.lang.management.ManagementFactory
import java.nio.file.Path
import kotlin.system.exitProcess

private const val DESKTOP = "Desktop-"
private const val ERROR_EXIT_CODE = -1

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

    val adbPathFromArguments by parser.option(
        type = ArgType.String,
        fullName = "adbPath",
        description = "Path to adb binary, if not set when use 'adb' from process environment"
    )

    parser.parse(args)

    val desktopName = getDesktopName()
    val desktopLogger = LoggerFactory.getDesktopLogger(logLevel, desktopName)

    val adbPath = adbPathFromArguments?.let { Path.of(it) } ?: findAdbAtPath()

    if (adbPath == null) {
        desktopLogger.e("Adb path not passed via arguments. Adb not found at path variable. Aborting...")
        exitProcess(ERROR_EXIT_CODE)
    }

    desktopLogger.i("Desktop started with arguments: emulators=$emulators, adbServerPort=$port, adbPath=$adbPath")

    val cmdCommandPerformer = CmdCommandPerformer(desktopName)
    val adbCommandPerformer = AdbCommandPerformer(adbPath, cmdCommandPerformer)
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

/**
 * Search for adb executable at environment PATH variable
 */
private fun findAdbAtPath(): Path? {
    return System.getenv("PATH")
        .split(":")
        .asSequence()
        .map { File(it) }
        .flatMap { it.listFiles()?.toList() ?: emptyList() }
        .firstOrNull { it.nameWithoutExtension == "adb" }?.toPath()
}
