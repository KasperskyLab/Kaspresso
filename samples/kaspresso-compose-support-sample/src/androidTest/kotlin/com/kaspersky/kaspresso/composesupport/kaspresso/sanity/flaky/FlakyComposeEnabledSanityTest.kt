package com.kaspersky.kaspresso.composesupport.kaspresso.sanity.flaky

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kaspersky.components.composesupport.config.withComposeSupport
import com.kaspersky.components.composesupport.interceptors.behavior.impl.flakysafety.FlakySafeSemanticsBehaviorInterceptor
import com.kaspersky.kaspresso.composesupport.sample.MainActivity
import com.kaspersky.kaspresso.composesupport.sample.screen.ComposeMainScreen
import com.kaspersky.kaspresso.composesupport.sample.screen.ComposeSanityFlakyScreen
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.logger.UiTestLoggerImpl
import com.kaspersky.kaspresso.params.FlakySafetyParams
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.compose.node.element.ComposeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FlakyComposeEnabledSanityTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.withComposeSupport(
        lateComposeCustomize = { composeBuilder ->
            composeBuilder.semanticsBehaviorInterceptors = mutableListOf(FlakySafeSemanticsBehaviorInterceptor(FlakySafetyParams.default(), UiTestLoggerImpl(Kaspresso.DEFAULT_TEST_LOGGER_TAG)))
        }
    )
) {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun test() = run {
        step("Open Flaky screen") {
            ComposeScreen.onComposeScreen<ComposeMainScreen>(composeTestRule) {
                sanityFlakyButton {
                    performClick()
                }
            }
        }

        step("Click on the First button") {
            ComposeScreen.onComposeScreen<ComposeSanityFlakyScreen>(composeTestRule) {
                firstButton {
                    assertIsDisplayed()
                    performClick()
                }
            }
        }

        step("Click on the Second button") {
            ComposeScreen.onComposeScreen<ComposeSanityFlakyScreen>(composeTestRule) {
                secondButton {
                    assertIsDisplayed()
                    performClick()
                }
            }
        }
    }
}
