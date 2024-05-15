package com.kaspersky.kaspresso.visual

import android.annotation.SuppressLint

@SuppressLint("SdCardPath")
data class VisualTestParams(
    val testType: VisualTestType = VisualTestType.Record,
    val hostScreenshotsDir: String = "original_screenshots",
    val colorTolerance: Int = 1,
    val tolerance: Float = 0.3f,
)
