package com.kaspersky.kaspresso.alluresupport.sample

import android.Manifest
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.components.alluresupport.visual.AllureVisualTestCase
import com.kaspersky.kaspresso.alluresupport.sample.screen.MainScreen
import org.junit.Rule
import org.junit.Test

class AllureVisualTest : AllureVisualTestCase(failEarly = false) {
    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @Test
    fun counter() = runScreenshotTest {
        step("Assert screenshot") {
            assertScreenshot("some_tag")
        }

        step("Usual checks") {
            MainScreen {
                incrementButton { isDisplayed() }
                decrementButton { isDisplayed() }
                clearButton { isDisplayed() }
                valueText { isDisplayed() }
            }
        }
    }
}
