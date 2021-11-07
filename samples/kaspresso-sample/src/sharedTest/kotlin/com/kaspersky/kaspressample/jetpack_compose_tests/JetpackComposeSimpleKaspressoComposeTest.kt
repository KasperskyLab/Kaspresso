package com.kaspersky.kaspressample.jetpack_compose_tests

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kaspersky.kaspressample.jetpack_compose.JetpackComposeActivity
import com.kaspersky.kaspressample.jetpack_compose_screen.ComposeMainScreen
import com.kaspersky.kaspressample.jetpack_compose_screen.ComposeSimpleFlakyScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.compose.node.element.ComposeScreen.Companion.onComposeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class JetpackComposeSimpleKaspressoComposeTest : TestCase() {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<JetpackComposeActivity>()

    private val simpleFlakyScreen = ComposeSimpleFlakyScreen(composeTestRule)

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
            simpleFlakyScreen {
                compose {
                    or(firstButton) {
                        assertIsDisplayed()
                    } thenContinue {
                        performClick()
                    }
                    or(secondButton) {
                        assertIsDisplayed()
                    } thenContinue {
                        performClick()
                    }
                }
            }
        }

        step("Click on the Second button") {
            simpleFlakyScreen {
                compose {
                    or(editText) {
                        assertIsDisplayed()
                    } thenContinue {
                        performClick()
                    }
                    or(secondButton) {
                        assertIsDisplayed()
                    } thenContinue {
                        performClick()
                    }
                }
            }
        }

        step("Interaction with EditText") {
            simpleFlakyScreen {
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
