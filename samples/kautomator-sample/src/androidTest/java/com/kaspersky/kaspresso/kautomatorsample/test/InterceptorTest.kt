package com.kaspersky.kaspresso.kautomatorsample.test

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.components.kautomator.KautomatorConfigurator
import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.components.kautomator.screen.UiScreen
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.elementloader.ElementLoaderObjectBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety.FlakySafeObjectBehaviorInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.kautomatorsample.MainActivity
import com.kaspersky.kaspresso.kautomatorsample.screen.MainScreen
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.ElementLoaderParams
import com.kaspersky.kaspresso.params.FlakySafetyParams
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

private lateinit var testElementLoaderParams: ElementLoaderParams
private lateinit var testFlakySafetyParams: FlakySafetyParams
private lateinit var logger: UiTestLogger

/**
 * The test demonstrating and checking work of interceptors concept in Kautomator
 */
class InterceptorTest : TestCase(kaspressoBuilder = Kaspresso.Builder.simple().apply {
    testElementLoaderParams = elementLoaderParams
    testFlakySafetyParams = flakySafetyParams
    logger = testLogger
}) {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    private val interceptorMainScreen = InterceptedMainScreen()
    private val objectBehaviorInterceptors = listOf(
        ElementLoaderObjectBehaviorInterceptor(testLogger, testElementLoaderParams),
        FlakySafeObjectBehaviorInterceptor(testFlakySafetyParams, testLogger)
    )

    @Test
    fun testKautomatorInterceptors() {
        val list = mutableListOf<String>()

        before {
            KautomatorConfigurator {
                intercept {
                    onUiInteraction {
                        onAll {
                            list.add("ALL")
                        }
                        onCheck { interaction, assertion ->
                            objectBehaviorInterceptors.fold(
                                initial = {
                                    interaction.check(assertion)
                                },
                                operation = { acc, objectBehaviorInterceptor ->
                                    { objectBehaviorInterceptor.interceptCheck(interaction, assertion, acc) }
                                }
                            ).invoke()
                            list.add("CHECK")
                        }
                        onPerform { interaction, action ->
                            objectBehaviorInterceptors.fold(
                                initial = {
                                    interaction.perform(action)
                                },
                                operation = {
                                        acc, objectBehaviorInterceptor ->
                                    { objectBehaviorInterceptor.interceptPerform(interaction, action, acc) }
                                }
                            ).invoke()
                            list.add("PERFORM")
                        }
                    }
                }
            }
        }.after {
        }.run {
            step("Simple action on default Screen") {
                MainScreen {
                    simpleButton {
                        isDisplayed()
                        click()
                    }
                }
            }

            step("Check Intercepting correctness") {
                assertEquals(mutableListOf("ALL", "CHECK", "ALL", "PERFORM"), list)
            }
        }
    }

    @Test
    fun testScreenInterceptors() = run {
        step("Simple action on Intercepted Screen") {
            interceptorMainScreen {
                simpleButton {
                    isDisplayed()
                    click()
                }
            }
        }

        step("Check Intercepting correctness") {
            interceptorMainScreen {
                assertEquals(mutableListOf("ALL", "CHECK", "ALL", "PERFORM"), screenList)
            }
        }
    }

    @Test
    fun testViewInterceptors() {
        val list = mutableListOf<String>()

        run {
            step("Simple action on default Screen with intercepted View") {
                MainScreen {
                    simpleButton {
                        intercept {
                            onAll { list.add("ALL") }
                            onCheck { interaction, assertion ->
                                objectBehaviorInterceptors.fold(
                                    initial = {
                                        interaction.check(assertion)
                                    },
                                    operation = { acc, objectBehaviorInterceptor ->
                                        { objectBehaviorInterceptor.interceptCheck(interaction, assertion, acc) }
                                    }
                                ).invoke()
                                list.add("CHECK")
                            }
                            onPerform { interaction, action ->
                                objectBehaviorInterceptors.fold(
                                    initial = {
                                        interaction.perform(action)
                                    },
                                    operation = {
                                            acc, objectBehaviorInterceptor ->
                                        { objectBehaviorInterceptor.interceptPerform(interaction, action, acc) }
                                    }
                                ).invoke()
                                list.add("PERFORM")
                            }
                        }

                        isDisplayed()
                        click()
                    }
                }
            }

            step("Check Intercepting correctness") {
                assertEquals(mutableListOf("ALL", "CHECK", "ALL", "PERFORM"), list)
            }
        }
    }

    @Test
    fun testNestedInterceptors() {
        val list = mutableListOf<String>()

        before {
            KautomatorConfigurator {
                intercept {
                    onUiInteraction {
                        onAll { list.add("ALL_KAUTOMATOR") }
                        onCheck { interaction, assertion ->
                            objectBehaviorInterceptors.fold(
                                initial = {
                                    interaction.check(assertion)
                                },
                                operation = { acc, objectBehaviorInterceptor ->
                                    { objectBehaviorInterceptor.interceptCheck(interaction, assertion, acc) }
                                }
                            ).invoke()
                            list.add("CHECK_KAUTOMATOR")
                        }
                        onPerform { interaction, action ->
                            objectBehaviorInterceptors.fold(
                                initial = {
                                    interaction.perform(action)
                                },
                                operation = {
                                        acc, objectBehaviorInterceptor ->
                                    { objectBehaviorInterceptor.interceptPerform(interaction, action, acc) }
                                }
                            ).invoke()
                            list.add("PERFORM_KAUTOMATOR")
                        }
                    }
                }
            }
        }.after {
        }.run {
            step("Simple action on intercepted Screen with intercepted View") {
                interceptorMainScreen {
                    simpleButton {
                        intercept {
                            onAll { list.add("ALL_VIEW") }
                            onCheck { _, _ -> list.add("CHECK_VIEW") }
                            onPerform { _, _ -> list.add("PERFORM_VIEW") }
                        }

                        isDisplayed()
                        click()
                    }
                }
            }

            step("Check Intercepting correctness") {
                interceptorMainScreen {
                    assert(screenList == mutableListOf("ALL", "CHECK", "ALL", "PERFORM"))
                }
                assertEquals(
                    mutableListOf(
                        "ALL_VIEW", "CHECK_VIEW", "ALL_KAUTOMATOR", "CHECK_KAUTOMATOR", "ALL_VIEW",
                        "PERFORM_VIEW", "ALL_KAUTOMATOR", "PERFORM_KAUTOMATOR"
                    ), list
                )
            }
        }
    }

    @Ignore("Previous interceptors are overridden when KautomatorConfigurator is used, so are default flaky safety interceptors, so this test may fail")
    @Test
    fun testOverridingInterceptors() {
        val list = mutableListOf<String>()

        before {
            KautomatorConfigurator {
                intercept {
                    onUiInteraction {
                        onAll { list.add("ALL_KAUTOMATOR") }
                        onCheck { _, _ -> list.add("CHECK_KAUTOMATOR") }
                        onPerform { _, _ -> list.add("PERFORM_KAUTOMATOR") }
                    }
                }
            }
        }.after {
        }.run {
            step("Simple action on intercepted Screen with intercepted View") {
                interceptorMainScreen {
                    simpleButton {
                        intercept {
                            onAll { list.add("ALL_VIEW") }

                            onCheck(true) { interaction, assertion ->
                                list.add("CHECK_VIEW")
                                interaction.check(assertion)
                            }

                            onPerform(true) { interaction, action ->
                                list.add("PERFORM_VIEW")
                                interaction.perform(action)
                            }
                        }

                        isDisplayed()
                        click()
                    }
                }
            }

            step("Check Intercepting correctness") {
                interceptorMainScreen {
                    assert(screenList.isEmpty())
                }
                assertEquals(mutableListOf("ALL_VIEW", "CHECK_VIEW", "ALL_VIEW", "PERFORM_VIEW"), list)
            }
        }
    }

    class InterceptedMainScreen : UiScreen<InterceptedMainScreen>() {

        override val packageName: String = "com.kaspersky.kaspresso.kautomatorsample"

        val screenList = mutableListOf<String>()
        val simpleButton = UiButton { withId(this@InterceptedMainScreen.packageName, "button") }

        private val objectBehaviorInterceptors = listOf(
            ElementLoaderObjectBehaviorInterceptor(logger, testElementLoaderParams),
            FlakySafeObjectBehaviorInterceptor(testFlakySafetyParams, logger)
        )

        init {
            intercept {
                onUiInteraction {
                    onAll { screenList.add("ALL") }
                    onCheck { interaction, assertion ->
                        objectBehaviorInterceptors.fold(
                            initial = {
                                interaction.check(assertion)
                            },
                            operation = { acc, objectBehaviorInterceptor ->
                                { objectBehaviorInterceptor.interceptCheck(interaction, assertion, acc) }
                            }
                        ).invoke()
                        screenList.add("CHECK")
                    }
                    onPerform { interaction, action ->
                        objectBehaviorInterceptors.fold(
                            initial = {
                                interaction.perform(action)
                            },
                            operation = {
                                    acc, objectBehaviorInterceptor ->
                                { objectBehaviorInterceptor.interceptPerform(interaction, action, acc) }
                            }
                        ).invoke()
                        screenList.add("PERFORM")
                    }
                }
            }
        }
    }
}
