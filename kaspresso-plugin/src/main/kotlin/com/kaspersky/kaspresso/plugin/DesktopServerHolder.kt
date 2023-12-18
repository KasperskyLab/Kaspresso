package com.kaspersky.kaspresso.plugin

import com.kaspersky.adbserver.common.log.LoggerFactory
import com.kaspersky.adbserver.common.log.logger.LogLevel
import com.kaspersky.adbserver.desktop.AdbCommandPerformer
import com.kaspersky.adbserver.desktop.CmdCommandPerformer
import com.kaspersky.adbserver.desktop.Desktop
import org.gradle.api.logging.Logger
import java.nio.file.Path

internal class DesktopServerHolder(private val logger: Logger) {
    companion object {
        private const val DESKTOP_NAME = "kaspresso-plugin-adb-server"
    }

    private var desktop: Desktop? = null

    @Synchronized
    fun start(workingDirectory: Path, adbPath: Path) {
        check(desktop == null) { "Desktop already started" }

        logger.debug("Starting Desktop server. workingDir=$workingDirectory, adbPath=$adbPath")

        val cmdCommandPerformer = CmdCommandPerformer(DESKTOP_NAME, workingDirectory)
        val adbCommandPerformer = AdbCommandPerformer(adbPath, cmdCommandPerformer)
        val logger = LoggerFactory.getDesktopLogger(LogLevel.VERBOSE, DESKTOP_NAME, GradleFullLogger(logger))
        desktop = Desktop(
            cmdCommandPerformer = cmdCommandPerformer,
            adbCommandPerformer = adbCommandPerformer,
            presetEmulators = emptyList(),
            adbServerPort = null,
            logger = logger,
            adbPath = adbPath.toString()
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
