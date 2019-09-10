package com.kaspersky.kaspressample.docloc_tests.advanced

import android.graphics.Color
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kaspersky.kaspressample.docloc.ScreenshotSampleFragment
import com.kaspersky.kaspressample.docloc.ScreenshotSampleView
import com.kaspersky.kaspresso.annotations.ScreenShooterTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AdvancedScreenshotSampleTest : ProductDocLocScreenshotTestCase() {

    private lateinit var fragment: ScreenshotSampleFragment
    private lateinit var view: ScreenshotSampleView

    @ScreenShooterTest
    @Test
    fun test() {
        before {
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
}