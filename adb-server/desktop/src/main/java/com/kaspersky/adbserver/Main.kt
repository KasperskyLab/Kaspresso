package com.kaspersky.adbserver

import com.kaspresky.adbserver.log.LoggerFactory
import java.lang.management.ManagementFactory

private const val DESKTOP = "Desktop-"

internal fun main(args: Array<String>) {
    val argsList = args.toList()
    val emulators = argsList
        .firstOrNull { arg -> arg.contains("emulators") }
        ?.removeSurrounding(prefix = "emulators", suffix = "")
        ?.replace("=", "")
        ?.split(",")
        ?.map { it.trim() }
        ?.filter { it.isNotEmpty() }
        ?: listOf()
    val adbServerPort = argsList
        .firstOrNull { arg -> arg.contains("adbServerPort") }
        ?.removeSurrounding(prefix = "adbServerPort", suffix = "")
        ?.replace("=", "")
        ?.trim()
    val runMode = argsList
            .firstOrNull { arg -> arg.contains("runMode") }
            ?.removeSurrounding(prefix = "runMode=", suffix = "")
            ?.trim()

    LoggerFactory.setRunMode(runMode)

    val processName = ManagementFactory.getRuntimeMXBean().name
    val pid = processName.split("@".toRegex()).toTypedArray()[0].toLong()
    val desktopName = DESKTOP + pid
    LoggerFactory.setDesktopName(desktopName)

    val logger = LoggerFactory.getLogger(tag = "Desktop")
    logger.i("MAIN", "arguments: emulators=$emulators, adbServerPort=$adbServerPort")

    val cmdCommandPerformer = CmdCommandPerformer()
    val desktop = Desktop(
        cmdCommandPerformer = cmdCommandPerformer,
        presetEmulators = emulators,
        adbServerPort = adbServerPort,
        desktopName = desktopName
    )
    desktop.startDevicesObserving()
}
