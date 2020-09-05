package com.kaspersky.adbserver

import com.kaspresky.adbserver.log.LoggerFactory
import com.kaspresky.adbserver.log.fulllogger.LogPolicy
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
    val logPolicy = LogPolicy.valueOf(
        argsList
            .firstOrNull { arg -> arg.contains("logPolicy") }
            ?.removeSurrounding(prefix = "logPolicy=", suffix = "")
            ?.trim()
            ?: LogPolicy.INFO.name
    )

    val desktopName = getDesktopName()
    val desktopLogger = LoggerFactory.getDesktopLogger(logPolicy, desktopName)

    desktopLogger.i("Desktop started with arguments: emulators=$emulators, adbServerPort=$adbServerPort")

    val cmdCommandPerformer = CmdCommandPerformer(desktopName)
    val desktop = Desktop(
        cmdCommandPerformer = cmdCommandPerformer,
        presetEmulators = emulators,
        adbServerPort = adbServerPort,
        logger = desktopLogger
    )
    desktop.startDevicesObserving()
}

private fun getDesktopName(): String {
    val processName = ManagementFactory.getRuntimeMXBean().name
    val pid = processName.split("@".toRegex()).toTypedArray()[0].toLong()
    return DESKTOP + pid
}
