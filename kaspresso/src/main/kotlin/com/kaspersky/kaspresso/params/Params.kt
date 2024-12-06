package com.kaspersky.kaspresso.params

import com.kaspersky.kaspresso.visual.VisualTestParams

/**
 * The facade class for all Kaspresso parameters.
 */
data class Params(
    val flakySafetyParams: FlakySafetyParams,
    val continuouslyParams: ContinuouslyParams,
    val autoScrollParams: AutoScrollParams,
    val stepParams: StepParams,
    val screenshotParams: ScreenshotParams,
    val videoParams: VideoParams,
    val elementLoaderParams: ElementLoaderParams,
    val systemDialogsSafetyParams: SystemDialogsSafetyParams,
    val clickParams: ClickParams,
    val visualTestParams: VisualTestParams,
)
