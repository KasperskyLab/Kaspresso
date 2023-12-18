package com.kaspersky.kaspresso.composesupport.kaspresso.sanity.autoscroll

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kaspersky.components.composesupport.config.withComposeSupport
import com.kaspersky.kaspresso.composesupport.sample.MainActivity
import com.kaspersky.kaspresso.composesupport.sample.screen.ComposeMainScreen
import com.kaspersky.kaspresso.composesupport.sample.screen.ComposeScrollScreen
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.compose.node.element.ComposeScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.AssertionError

@RunWith(AndroidJUnit4::class)
class AutoScrollSemanticsDisabledSanityTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.withComposeSupport(
        lateComposeCustomize = { composeBuilder ->
            composeBuilder.semanticsBehaviorInterceptors = mutableListOf()
        }
    )
) {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun test() = run {
        step("Open Flaky screen") {
            ComposeScreen.onComposeScreen<ComposeMainScreen>(composeTestRule) {
                scrollButton {
                    performClick()
                }
            }
        }

        step("Click on the First button") {
            ComposeScreen.onComposeScreen<ComposeScrollScreen>(composeTestRule) {
                firstButton {
                    assertIsDisplayed()
                    performClick()
                }
            }
        }

        step("Click on the Last button") {
            ComposeScreen.onComposeScreen<ComposeScrollScreen>(composeTestRule) {
                Assert.assertThrows(null, AssertionError::class.java) {
                    lastButton {
                        assertIsDisplayed()
                        performClick()
                    }
                }
            }
        }
    }
}
