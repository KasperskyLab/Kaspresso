package com.kaspersky.kaspresso.device.screenshots.screenshoter.external

import java.io.File

internal object Chmod {

    fun chmodPlusR(file: File) {
        file.setReadable(true, false)
    }

    fun chmodPlusRWX(file: File) {
        file.setReadable(true, false)
        file.setWritable(true, false)
        file.setExecutable(true, false)
    }
}
