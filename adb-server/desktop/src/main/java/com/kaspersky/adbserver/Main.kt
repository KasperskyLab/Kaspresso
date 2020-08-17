package com.kaspersky.adbserver

import com.kaspresky.adbserver.log.LoggerFactory

private const val DEFAULT_ADB_PORT = "5037"

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

    if (!adbServerPort.isNullOrEmpty()) {
        LoggerFactory.setDesktopName(adbServerPort)
    } else {
        LoggerFactory.setDesktopName(DEFAULT_ADB_PORT)
    }

    val logger = LoggerFactory.getLogger(tag = "Desktop")
    logger.i("MAIN", "arguments: emulators=$emulators, adbServerPort=$adbServerPort")

    val cmdCommandPerformer = CmdCommandPerformer()
    val desktop = Desktop(
        cmdCommandPerformer = cmdCommandPerformer,
        presetEmulators = emulators,
        adbServerPort = adbServerPort
    )
    desktop.startDevicesObserving()
}
