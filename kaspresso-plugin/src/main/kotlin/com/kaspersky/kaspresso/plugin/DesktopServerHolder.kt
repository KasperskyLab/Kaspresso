package com.kaspersky.kaspresso.plugin

import com.kaspersky.adbserver.common.log.LoggerFactory
import com.kaspersky.adbserver.common.log.logger.LogLevel
import com.kaspersky.adbserver.desktop.CmdCommandPerformer
import com.kaspersky.adbserver.desktop.Desktop
import java.nio.file.Path

internal class DesktopServerHolder {
    companion object {
        private const val DESKTOP_NAME = "kaspresso-plugin-adb-server"
    }

    private var desktop: Desktop? = null

    @Synchronized
    fun start(workingDirectory: Path) {
        check(desktop == null) { "Desktop already started" }
        val cmdCommandPerformer = CmdCommandPerformer(DESKTOP_NAME, workingDirectory)
        desktop = Desktop(
            cmdCommandPerformer = cmdCommandPerformer,
            presetEmulators = emptyList(),
            adbServerPort = null,
            logger = LoggerFactory.getDesktopLogger(LogLevel.INFO, DESKTOP_NAME)
        )
            .apply { startDevicesObservingAsync() }
    }

    @Synchronized
    fun stop() {
        check(desktop != null) { "Desktop not started" }
        desktop!!.stopDevicesObserving()
        desktop = null
    }
}
