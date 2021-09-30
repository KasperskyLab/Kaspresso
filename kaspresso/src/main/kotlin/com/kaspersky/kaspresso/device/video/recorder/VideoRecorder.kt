package com.kaspersky.kaspresso.device.video.recorder

import java.io.File

interface VideoRecorder {

    /**
     * Starts recording the video to the file
     */
    fun start(file: File)

    /**
     * Stops recording the video
     * @return Result file or null in case of errors
     */
    fun stop(): File?
}
