package com.kaspersky.kaspresso.device.video.videorecorder

import android.util.Log
import com.kaspersky.kaspresso.internal.wait.wait
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.VideoParams
import java.io.File

class VideoRecorderImpl(
    private val logger: UiTestLogger,
    private val params: VideoParams
) : VideoRecorder {

    private var videoRecordingThread: VideoRecordingThread? = null

    /**
     * Starts video recording. It must be manually finished with [stop] afterwards.
     */
    override fun start(file: File) {
        if (videoRecordingThread != null) {
            logger.i("Can't start video recording as it is already started: ${file.name}")
            return
        }
        logger.i("Starting video recording: ${file.name}")

        videoRecordingThread = VideoRecordingThread(logger, params, file).apply { start() }
        waitForRecordingToStart()
    }

    override fun stop(): File? {
        if (videoRecordingThread == null) {
            logger.i("Can't stop video recording as it was not started")
            return null
        }
        logger.i("Stopping video recording")

        val thread: VideoRecordingThread = videoRecordingThread!!
        thread.killRecordingProcess()
        doAfterRecordingStopped {
            thread.interrupt()
            videoRecordingThread = null
        }
        return thread.file
    }

    private fun waitForRecordingToStart(): Unit? = wait(
        timeoutMs = params.startRecordingTimeMs,
        logger = logger,
        allowedExceptions = setOf(InterruptedException::class.java),
        failureMessageSource = ::getErrorMessage,
        action = null
    )

    private fun doAfterRecordingStopped(action: () -> Unit): Unit? = wait(
        timeoutMs = params.stopRecordingTimeMs,
        logger = logger,
        allowedExceptions = setOf(InterruptedException::class.java),
        failureMessageSource = ::getErrorMessage,
        action = action
    )

    private fun getErrorMessage(error: Throwable): String =
        "Video recording was interrupted:\n${Log.getStackTraceString(error)}"
}
