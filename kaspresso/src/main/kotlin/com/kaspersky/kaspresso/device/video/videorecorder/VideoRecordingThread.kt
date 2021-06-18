package com.kaspersky.kaspresso.device.video.videorecorder

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.VideoParams
import java.io.File

class VideoRecordingThread(
    private val logger: UiTestLogger,
    private val params: VideoParams,
    val file: File
) : Thread() {

    override fun start() {
        priority = MAX_PRIORITY
        super.start()
    }

    override fun run() {
        execShellCommand("screenrecord --bit-rate ${params.bitRate} --bugreport ${file.absolutePath}")
    }

    fun killRecordingProcess() {
        execShellCommand("pkill -l INT screenrecord")
    }

    private fun execShellCommand(command: String) {
        try {
            UiDevice.getInstance(
                InstrumentationRegistry.getInstrumentation()
            ).executeShellCommand(command).trim()
        } catch (e: Throwable) {
            logger.e("Adb shell command:\n${command}\nexecution failure: ${e.message}")
        }
    }
}
