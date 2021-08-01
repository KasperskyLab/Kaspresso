package com.kaspersky.kaspresso.device.video.recorder

import java.io.File

interface VideoRecorder {

    /**
     * Starts recording a video to the file
     */
    fun start(file: File)

    /**
     * Stop recording the video
     * @return Result file or null in case of errors
     */
    fun stop(): File?
}
