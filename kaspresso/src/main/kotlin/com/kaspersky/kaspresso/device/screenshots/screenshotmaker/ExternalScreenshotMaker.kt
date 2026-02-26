package com.kaspersky.kaspresso.device.screenshots.screenshotmaker

import android.app.Instrumentation
import androidx.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.instrumental.InstrumentalDependencyProvider
import com.kaspersky.kaspresso.params.ScreenshotParams
import java.io.File

/**
 * Captures spoon-compatible screenshots by uiautomator.
 *
 * Unlike [InternalScreenshotMaker] which only captures the main activity window,
 * this maker uses [UiDevice.takeScreenshot] to capture the full device screen,
 * including popup windows such as Material ModalBottomSheet.
 */
class ExternalScreenshotMaker(
    private val instrumentalDependencyProvider: InstrumentalDependencyProvider,
    private val params: ScreenshotParams = ScreenshotParams()
) : ScreenshotMaker {

    /**
     * Creates an [ExternalScreenshotMaker] from an [Instrumentation] instance.
     *
     * This is a convenience constructor for use in [com.kaspersky.kaspresso.kaspresso.Kaspresso.Builder]:
     * ```
     * Kaspresso.Builder.simple {
     *     screenshotMaker = ExternalScreenshotMaker(instrumentation)
     * }
     * ```
     */
    constructor(
        instrumentation: Instrumentation,
        params: ScreenshotParams = ScreenshotParams()
    ) : this(SimpleUiDeviceProvider(instrumentation), params)

    private val device: UiDevice
        get() = instrumentalDependencyProvider.uiDevice

    // Somehow scale param is not used in UiDevice#takeScreenshot method,
    // so just using default here
    private val scale: Float = 1.0f

    override fun takeScreenshot(file: File) {
        device.takeScreenshot(file, scale, params.quality)
    }

    override fun takeFullWindowScreenshot(file: File) = takeScreenshot(file)

    /**
     * Minimal [InstrumentalDependencyProvider] that only provides [UiDevice].
     * Used by the [Instrumentation]-based constructor to avoid depending on the full provider implementation.
     */
    private class SimpleUiDeviceProvider(
        private val instrumentation: Instrumentation
    ) : InstrumentalDependencyProvider {
        override val isAndroidRuntime: Boolean = true
        override val uiDevice: UiDevice get() = UiDevice.getInstance(instrumentation)
        override val uiAutomation get() = instrumentation.uiAutomation
        override val runNotifier get() = throw UnsupportedOperationException()
        override fun getUiAutomation(flags: Int) = instrumentation.getUiAutomation(flags)
    }
}
