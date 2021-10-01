package com.kaspersky.kaspresso.files.resources.impl

import com.kaspersky.kaspresso.device.screenshots.screenshotfiles.ScreenshotDirectoryProvider
import com.kaspersky.kaspresso.files.models.TestMethod
import com.kaspersky.kaspresso.files.resources.ResourcesDirNameProvider

/**
 * Special mapper between new and old systems of resource providing
 */
internal class SupportLegacyResourcesDirNameProvider(
    private val screenshotDirectoryProvider: ScreenshotDirectoryProvider,
) : ResourcesDirNameProvider {

    companion object {
        const val DEFAULT_RUN_NUMBER = 1
    }

    override fun provideResourcesDirName(testMethod: TestMethod): String {
        val relocatedTestMethod = com.kaspersky.kaspresso.device.screenshots.screenshotfiles.TestMethod(
            className = testMethod.className,
            methodName = testMethod.methodName
        )
        return screenshotDirectoryProvider.getDirectoryForTest(relocatedTestMethod, DEFAULT_RUN_NUMBER)
    }
}
