package com.kaspersky.kaspresso.device.video.recorder

import android.app.Instrumentation
import android.content.Context
import android.hardware.display.DisplayManager
import android.media.MediaCodecList
import android.os.Build
import android.view.Display
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.Resolution
import com.kaspersky.kaspresso.params.VideoParams
import java.io.File
import kotlin.math.min

class VideoRecordingThread(
    private val device: UiDevice,
    private val logger: UiTestLogger,
    private val params: VideoParams,
    val file: File,
    private val instrumentation: Instrumentation
) : Thread() {

    override fun start() {
        priority = MAX_PRIORITY
        super.start()
    }

    override fun run() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            startVideoRecordingWithNativeResolution()
        } else {
            startVideoRecordingWithRespectToCodecCapabilities()
        }
    }

    private fun startVideoRecordingWithNativeResolution() {
        execShellCommand("screenrecord --bit-rate ${params.bitRate} --bugreport ${file.absolutePath}")
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun startVideoRecordingWithRespectToCodecCapabilities() {
        try {
            val resolution = getResolution()
            logger.d("Starting video recording with resolution ${resolution.width}x${resolution.height}")
            execShellCommand("screenrecord --bit-rate ${params.bitRate} --size ${resolution.width}x${resolution.height} --bugreport ${file.absolutePath}")
        } catch (ex: Throwable) {
            logger.e(
                "Failed to start video recording with respect to resolution supported by codec. Using native resolution. " +
                        "NOTE: not all devices support native resolution video recording: https://developer.android.com/studio/command-line/adb.html#screenrecord"
            )
            ex.message?.let { logger.e(it) }
            logger.e(ex.stackTraceToString())

            startVideoRecordingWithNativeResolution()
        }
    }

    private fun getResolution(): Resolution {
        if (params.resolutionOverride != null) {
            logger.d("Using video resolution override=${params.resolutionOverride}")
            return params.resolutionOverride
        }

        val codecInfo = MediaCodecList(MediaCodecList.ALL_CODECS).codecInfos
            .filter { it.isEncoder }
            .find { it.name.contains("h264") }!!
        val videoCapabilities = codecInfo.getCapabilitiesForType(codecInfo.supportedTypes.find { it.contains("avc") }).videoCapabilities
        // codec width and heights are for landscape mode
        val codecHeight = videoCapabilities.supportedWidths.upper
        val codecWidth = videoCapabilities.supportedHeights.upper

        val display = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            instrumentation.targetContext
                .getSystemService(DisplayManager::class.java)
                .getDisplay(Display.DEFAULT_DISPLAY)
        } else {
            (instrumentation.targetContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager?)?.defaultDisplay!!
        }
        val displayWidth = display.width
        val displayHeight = display.height

        val width = min(displayWidth, codecWidth)
        val height = min(displayHeight, codecHeight)

        logger.d("Display resolution: ${displayWidth}x$displayHeight; supported codec resolution: ${codecWidth}x$codecHeight")

        return Resolution(width = width, height = height)
    }

    fun killRecordingProcess() {
        execShellCommand("pkill -l INT screenrecord")
    }

    private fun execShellCommand(command: String) {
        try {
            device.executeShellCommand(command).trim()
        } catch (e: Throwable) {
            logger.e("Adb shell command:\n${command}\nexecution failure: ${e.message}")
        }
    }
}
