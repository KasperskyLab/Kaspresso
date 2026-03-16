package com.kaspersky.kaspressample.configurator_tests.interceptor_tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.kaspresso.removeInterceptors
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

/**
 * Demonstrates the [removeInterceptors] API for disabling interceptors across all lists
 * in a single call.
 *
 * Before this API existed, removing an interceptor type required one call per list:
 * ```
 * kaspressoBuilder = Kaspresso.Builder.simple().apply {
 *     viewBehaviorInterceptors.removeIf { it is FlakySafetyProvider }
 *     dataBehaviorInterceptors.removeIf { it is FlakySafetyProvider }
 *     webBehaviorInterceptors.removeIf { it is FlakySafetyProvider }
 *     objectBehaviorInterceptors.removeIf { it is FlakySafetyProvider }
 *     deviceBehaviorInterceptors.removeIf { it is FlakySafetyProvider }
 * }
 * ```
 *
 * With [removeInterceptors] the same result is achieved in one line:
 * ```
 * kaspressoBuilder = Kaspresso.Builder.simple().apply {
 *     removeInterceptors<FlakySafetyProvider>()
 * }
 * ```
 */
class RemoveInterceptorsTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple().apply {
        // Disables all flaky-safety retries across every interceptor list.
        // Without this, Kaspresso retries failing view interactions for up to 10 seconds.
        removeInterceptors<FlakySafetyProvider>()
    }
) {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() = run {
        step("Check that the main screen is displayed") {
            MainScreen {
                descriptionText {
                    isVisible()
                }
            }
        }

        step("Verify buttons are present without flaky-safety retries") {
            MainScreen {
                simpleButton { isVisible() }
                flakyButton { isVisible() }
            }
        }
    }
}
