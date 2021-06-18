package com.kaspersky.kaspressample.docloc_tests.advanced

import android.Manifest
import android.graphics.Color
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspresso_sample_core.docloc.ScreenshotSampleFragment
import com.kaspersky.kaspresso_sample_core.docloc.ScreenshotSampleView
import com.kaspersky.kaspresso.annotations.ScreenShooterTest
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import org.junit.Rule
import org.junit.Test

/**
 * An example of advanced [DocLocScreenshotTestCase] usage.
 * For more information see DocLoc wiki page.
 */
class AdvancedScreenshotSampleTest : ProductDocLocScreenshotTestCase() {

    private lateinit var fragment: ScreenshotSampleFragment
    private lateinit var view: ScreenshotSampleView

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @ScreenShooterTest
    @Test
    fun test() = before {
        fragment = ScreenshotSampleFragment()
        view = getUiSafeProxy(fragment as ScreenshotSampleView)
        activity.setFragment(fragment)
    }.after {
    }.run {
        step("1. Launch feature screen") {
            view.setCounterValue(0)
            view.setBackgroundColor(Color.WHITE)
            captureScreenshot("1. Startup")
        }

        step("2. Increase counter by 5") {
            view.setCounterValue(5)
            captureScreenshot("2. Value has been increased by 5")
        }

        step("3. Set red background color") {
            view.setBackgroundColor(Color.RED)
            captureScreenshot("3. Background has been set to red")
        }
    }
}
