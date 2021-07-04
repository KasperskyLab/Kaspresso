package com.kaspersky.kaspresso.device.video.recorder

import java.io.File

interface VideoRecorder {
    fun start(file: File)
    fun stop(): File?
}
