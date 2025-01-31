package com.kaspersky.kaspresso.params

/**
 * @param quality quality of the PNG compression; range: 0-100
 * @param metadataExtractor determines the API used to get metadata from the app
 */
class ScreenshotParams(
    val quality: Int = 100,
    val metadataExtractor: MetadataExtractors = MetadataExtractors.Default,
)

enum class MetadataExtractors {
    /**
     * Traverses XML's views hierarchy to get views metadata
      */
    Default,

    /**
     * Dumps and traverses UI automator tree to get views metadata. Recommended to use for Compose screens
     */
    UiAutomator,
}
