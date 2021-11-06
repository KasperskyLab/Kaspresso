package com.kaspersky.kaspressample.jetpack_compose_tests

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.kaspersky.kaspressample.jetpack_compose.JetpackComposeActivity
import com.kaspersky.kaspressample.jetpack_compose_screen.ComposeMainScreen
import com.kaspersky.kaspressample.jetpack_compose_screen.ComposeSimpleFlakyScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.compose.node.element.ComposeScreen
import org.junit.Rule
import org.junit.Test

class ComposeSimpleFlakyTest : TestCase() {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<JetpackComposeActivity>()

    @Test
    fun test() = run {
        step("Open Flaky screen") {
            ComposeScreen.onComposeScreen<ComposeMainScreen>(composeTestRule) {
                simpleFlakyButton {
                    performClick()
                }
            }
        }

        step("Click on the First button") {
            ComposeScreen.onComposeScreen<ComposeSimpleFlakyScreen>(composeTestRule) {
                firstButton {
                    assertIsDisplayed()
                    performClick()
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
