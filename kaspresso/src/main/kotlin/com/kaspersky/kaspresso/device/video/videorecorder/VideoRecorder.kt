package com.kaspersky.kaspresso.device.video.videorecorder

import java.io.File

interface VideoRecorder {
    fun start(file: File)
    fun stop(): File?
}
