package com.kaspersky.kaspressample.configurator_tests.enricher_tests

import androidx.test.rule.ActivityTestRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.SimpleScreen
import org.junit.Rule
import org.junit.Test

class MainSectionEnricherTest : EnricherTestCase() {

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, true)

    @Test
    fun enricherTestCase() {
        init {
            user {
                post { title = "First post" }
                post { title = "Second post" }
            }
        }.run {
            step("Open simple screen") {
                MainScreen {
                    simpleButton.click()
                }
            }
            step("Click on first button") {
                SimpleScreen {
                    button1.click()
                }
            }
        }
    }
}