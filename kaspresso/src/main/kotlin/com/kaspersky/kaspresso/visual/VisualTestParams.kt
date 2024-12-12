package com.kaspersky.kaspresso.visual

/**
 * @see com.kaspersky.kaspresso.internal.visual.DefaultScreenshotsComparator
 */
data class VisualTestParams(
    /**
     * Controls whether to take the new reference screenshots and save them or use the old ones and compare them to the ones being taken during the test
     */
    val testType: VisualTestType = VisualTestType.Record,
    /**
     * The path with the reference screenshots. Used to save the new reference screenshots if testType is set to the VisualTestType.Record
     * or to push screenshot files if testType is set to VisualTestType.Compare
     */
    val hostScreenshotsDir: String = "original_screenshots",
    /**
     * The color threshold to mark the single pixel different from the other one.
     * @see com.kaspersky.kaspresso.internal.visual.DefaultScreenshotsComparator.checkColors
     */
    val colorTolerance: Int = 1,
    /**
     * Controls the threshold of the screenshots difference. The value is in percents. Screenshots with difference less than this value
     * are "acceptable" and don't fail the test
     * @see com.kaspersky.kaspresso.internal.visual.DefaultScreenshotsComparator.compare
     */
    val tolerance: Float = 0.3f,
)
