package com.kaspersky.kaspressample.jetpack_compose_tests

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kaspersky.components.composesupport.config.withComposeSupport
import com.kaspersky.kaspressample.jetpackcompose.JetpackComposeActivity
import com.kaspersky.kaspressample.jetpack_compose_screen.ComposeMainScreen
import com.kaspersky.kaspressample.jetpack_compose_screen.ComposeSimpleFlakyScreen
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.compose.node.element.ComposeScreen.Companion.onComposeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComposeSimpleFlakyTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.withComposeSupport()
) {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<JetpackComposeActivity>()

    @Test
    fun test() = run {
        step("Open Flaky screen") {
            onComposeScreen<ComposeMainScreen>(composeTestRule) {
                simpleFlakyButton {
                    performClick()
                }
            }
        }

        step("Click on the First button") {
            onComposeScreen<ComposeSimpleFlakyScreen>(composeTestRule) {
                firstButton {
                    assertIsDisplayed()
                    performClick()
                }
            }
        }

        step("Click on the Second button") {
            onComposeScreen<ComposeSimpleFlakyScreen>(composeTestRule) {
                secondButton {
                    assertIsDisplayed()
                    performClick()
                }
            }
        }

        step("Interaction with EditText") {
            onComposeScreen<ComposeSimpleFlakyScreen>(composeTestRule) {
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
