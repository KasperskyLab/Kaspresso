package com.kaspersky.kaspresso.device.video.recorder

import android.util.Log
import com.kaspersky.kaspresso.internal.extensions.other.isAllowed
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

    private fun <T> wait(
        timeoutMs: Long,
        logger: UiTestLogger,
        allowedExceptions: Set<Class<out Throwable>>,
        failureMessageSource: (Throwable) -> String?,
        action: (() -> T)?
    ): T? {
        logger.i("Waiting for $timeoutMs ms")
        return try {
            Thread.sleep(timeoutMs)
            action?.invoke()
        } catch (error: Throwable) {
            if (error.isAllowed(allowedExceptions)) {
                logger.e(failureMessageSource(error) ?: "An error occurred while waiting: ${error.javaClass.simpleName}")
                null
            } else {
                throw error
            }
        }
    }
}
