package com.kaspersky.kaspresso.device.video.recorder

import android.app.Instrumentation
import android.util.Log
import androidx.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.instrumental.InstrumentalDependencyProvider
import com.kaspersky.kaspresso.internal.wait.wait
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.VideoParams
import java.io.File

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

class VideoRecorderImpl(
    private val instrumentalDependencyProvider: InstrumentalDependencyProvider,
    private val logger: UiTestLogger,
    private val params: VideoParams,
    private val instrumentation: Instrumentation
) : VideoRecorder {

    private val device: UiDevice
        get() = instrumentalDependencyProvider.uiDevice
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

        videoRecordingThread = VideoRecordingThread(device, logger, params, file, instrumentation).apply { start() }
        waitForRecordingToStart()
    }

    override fun stop(): File? {
        val thread: VideoRecordingThread = videoRecordingThread ?: run {
            logger.i("Can't stop video recording as it was not started")
            return null
        }

        logger.i("Stopping video recording")
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
