package com.kaspersky.kaspresso.visual

import java.io.File

interface ScreenshotsComparator {
    fun compare(originalScreenshot: File, newScreenshot: File): Boolean
}
