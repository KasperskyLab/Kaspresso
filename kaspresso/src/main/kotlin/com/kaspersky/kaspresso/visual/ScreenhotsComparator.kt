package com.kaspersky.kaspresso.visual

import java.io.File
import java.io.FileInputStream

interface ScreenshotsComparator {
    fun compare(originalScreenshot: File, newScreenshot: File): Boolean
}
