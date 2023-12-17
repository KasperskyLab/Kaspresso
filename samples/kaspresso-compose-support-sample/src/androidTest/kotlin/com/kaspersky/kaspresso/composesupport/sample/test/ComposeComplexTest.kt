package com.kaspersky.kaspresso.composesupport.sample.test

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kaspersky.components.composesupport.config.withComposeSupport
import com.kaspersky.kaspresso.composesupport.sample.MainActivity
import com.kaspersky.kaspresso.composesupport.sample.screen.ComposeMainScreen
import com.kaspersky.kaspresso.composesupport.sample.screen.ComposeSimpleFlakyScreen
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.compose.node.element.ComposeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComposeComplexTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.withComposeSupport()
) {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun test() = run {
        step("Open Flaky screen") {
            ComposeScreen.onComposeScreen<ComposeMainScreen>(composeTestRule) {
                simpleFlakyButton {
                    performClick()
                }
            }
        }

        step("Flow is over the product") {
            ComposeScreen.onComposeScreen<ComposeSimpleFlakyScreen>(composeTestRule) {
                compose {
                    // the first potential branch when firstButton is visible
                    or(firstButton) {
                        assertIsDisplayed()
                    } thenContinue {
                        performClick()
                    }
                    // the second potential branch when secondButton is visible
                    or(secondButton) {
                        assertIsDisplayed()
                    } thenContinue {
                        performClick()
                    }
                }
            }
        }

        step("Click on the Second button") {
            ComposeScreen.onComposeScreen<ComposeSimpleFlakyScreen>(composeTestRule) {
                secondButton {
                    assertIsDisplayed()
                    performClick()
                }
            }
        }

        step("Interaction with EditText") {
            ComposeScreen.onComposeScreen<ComposeSimpleFlakyScreen>(composeTestRule) {
                editText {
                    assertIsDisplayed()
                    assertTextContains("Some text")
                    performTextClearance()
                    performTextInput("New text")
                    assertTextContains("New text")
                }
            }
        }
    }
}
