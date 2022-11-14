package com.kaspersky.kaspresso.plugin

import com.kaspersky.adbserver.common.log.LoggerFactory
import com.kaspersky.adbserver.common.log.logger.LogLevel
import com.kaspersky.adbserver.desktop.Desktop
import org.gradle.kotlin.dsl.provideDelegate

internal object AdbServerUtils {
    private const val DESKTOP_NAME = "kaspresso-plugin-adb-server"

    private val desktop: Desktop by lazy {
        Desktop(
            desktopName = DESKTOP_NAME,
            presetEmulators = emptyList(),
            adbServerPort = null,
            logger = LoggerFactory.getDesktopLogger(LogLevel.INFO, DESKTOP_NAME)
        )
    }

    fun start() {
        desktop.startDevicesObservingAsync()
    }

    fun stop() {
        desktop.stopDevicesObserving()
    }

}
