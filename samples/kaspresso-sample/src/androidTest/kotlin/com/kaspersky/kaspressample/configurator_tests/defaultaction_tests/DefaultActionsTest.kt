package com.kaspersky.kaspressample.configurator_tests.defaultaction_tests

import android.Manifest
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.SimpleScreen
import org.junit.After
import org.junit.Rule
import org.junit.Test

class DefaultActionsTest : ParentTestCase(
    kaspressoBuilderAdditional = {
        beforeEachTest {
            testLogger.i("beforeTestSecondAction")
            DefaultActionsChecker.putBeforeInTestCase()
        }
        afterEachTest(override = true) {
            testLogger.i("afterTestSecondAction")
            DefaultActionsChecker.putAfterInTestCase()
        }
    }
) {
    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @After
    fun afterTest() {
        DefaultActionsChecker.assertAfter()
        DefaultActionsChecker.reset()
    }

    @Test
    fun test() {
        before {
            testLogger.i("beforeSection")
            DefaultActionsChecker.putBeforeInBeforeSection()
            DefaultActionsChecker.assertBefore()
        }.after {
            testLogger.i("afterSection")
            DefaultActionsChecker.putAfterInAfterSection()
        }.run {

            step("Open Simple Screen") {
                MainScreen {
                    simpleButton {
                        isVisible()
                        click()
                    }
                }
            }

            step("Click button 1 and check button 2") {
                SimpleScreen {
                    button1.click()
                    button2.isVisible()
                }
            }

            step("Click button 2 and check edit") {
                SimpleScreen {
                    button2.click()
                    edit {
                        flakySafely(timeoutMs = 5000) { isVisible() }
                        hasText(R.string.simple_fragment_text_edittext)
                    }
                }
            }
        }
    }
}
