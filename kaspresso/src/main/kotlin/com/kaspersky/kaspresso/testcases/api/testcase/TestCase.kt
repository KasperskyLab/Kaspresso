package com.kaspersky.kaspresso.testcases.api.testcase

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import com.agoda.kakao.common.assertions.BaseAssertions
import com.agoda.kakao.intercept.Interceptable
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.interceptors.interactors.ViewInteractor
import com.kaspersky.kaspresso.proxy.ViewAssertionProxy

/**
 *  A base class for all test cases. Extend this class with a single base project-wide inheritor of [TestCase] as a
 *  parent for all actual project-wide test cases. Nesting test cases are not permitted because they may produce an
 *  exception caused by re-initialization of the [Configurator], use
 *  [com.kaspersky.kaspresso.testcases.api.scenario.Scenario] instead.
 */
abstract class TestCase(
    configBuilder: Configurator.Builder = Configurator.Builder.default()
) : BaseTestCase<Unit, Unit>(
    configBuilder = configBuilder,
    dataProducer = { action -> action?.invoke(Unit) }
) {
    fun <T> T.compositeCheck(
        vararg asserts: T.() -> Unit
    ) where T : BaseAssertions, T : Interceptable<ViewInteraction, ViewAssertion, ViewAction> {

        Configurator.logger.i("1 compositeCheck: setting interceptors")

        intercept {
            onCheck(true) { viewInteraction: ViewInteraction, viewAssertion: ViewAssertion ->

                Configurator.logger.i("3 compositeCheck: got interaction, setting new interceptors")

                intercept {
                    onCheck(true) { viewInteraction: ViewInteraction, viewAssertion: ViewAssertion ->
                        Configurator.logger.i("9 compositeCheck: in final interceptor for each assert")

                        viewInteraction.check(ViewAssertionProxy(viewAssertion, configurator.viewAssertionInterceptors))
                    }
                }

                Configurator.logger.i("4 compositeCheck: creating mega view interactor")

                configurator.viewInteractors.fold(
                    {
                        Configurator.logger.i("6 compositeCheck: started running asserts")

                        var cachedThrowable: Throwable? = null

                        Configurator.logger.i("Composite check started.")

                        asserts.forEach { assert: T.() -> Unit ->
                            Configurator.logger.i("7 compositeCheck: in foreach")

                            try {
                                Configurator.logger.i("8 compositeCheck: in try block")

                                assert.invoke(this@compositeCheck)

                                Configurator.logger.i("10 compositeCheck: returning from try")

                                Configurator.logger.i("Composite check successfully passed.")
                                return@fold
                            } catch (e: Throwable) {
                                Configurator.logger.i("11 compositeCheck: in catch")

                                cachedThrowable = e
                                Configurator.logger.i("One part of composite check failed.")
                            }
                        }

                        Configurator.logger.i("12 compositeCheck: throwing from fold")

                        Configurator.logger.i("Composite check totally failed.")
                        throw cachedThrowable!!
                    },
                    { acc, viewInteractor: ViewInteractor -> {
                        Configurator.logger.i("5 compositeCheck: wrapping interactors")

                        viewInteractor.interact(viewInteraction, acc)
                    } }
                ).invoke()
            }
        }

        Configurator.logger.i("2 compositeCheck, invoke first assert to get interaction")

        asserts.first().invoke(this)
    }
}