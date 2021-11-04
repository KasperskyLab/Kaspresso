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
        step("1") {
            ComposeScreen.onComposeScreen<ComposeMainScreen>(composeTestRule) {
                simpleFlakyButton {
                    performClick()
                }
            }
        }

        step("2") {
            ComposeScreen.onComposeScreen<ComposeSimpleFlakyScreen>(composeTestRule) {
                firstButton {
                    assertIsDisplayed()
                    performClick()
                }

                // todo remove it
                Thread.sleep(5_000)

                secondButton {
                    assertIsDisplayed()
                    performClick()
                }

                // todo remove it
                Thread.sleep(5_000)

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
