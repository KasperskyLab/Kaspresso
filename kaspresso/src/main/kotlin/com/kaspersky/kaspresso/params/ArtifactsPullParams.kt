package com.kaspersky.kaspresso.params

data class ArtifactsPullParams(
    /**
     * Relative path. Absolute one depends on the working directory from which ADB server was started
     */
    val destinationPath: String = ".",

    /**
     * Artifacts would be pulled if it's name fits regex
     */
    val artifactsRegex: Regex = "(screenshots)|(video)|(logcat)|(view_hierarchy)".toRegex(),

    /**
     * Whether Kaspresso should pull the artifacts after a test run. Needs an ADB server to work
     */
    val enabled: Boolean = true
)
