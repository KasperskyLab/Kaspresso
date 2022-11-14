package com.kaspersky.kaspresso.plugin

import org.gradle.api.provider.Property
import java.nio.file.Path

abstract class KaspressoExtension {
    // by default using rootProject directory
    abstract val workingDirectory: Property<Path>

    // by default use project.android.sdkDirectory/platform-tools/adb
    abstract val adbPath: Property<Path>
}
