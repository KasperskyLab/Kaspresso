package com.kaspersky.kaspresso.testcases.core.testcontext

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import com.agoda.kakao.common.actions.BaseActions
import com.agoda.kakao.common.assertions.BaseAssertions
import com.agoda.kakao.intercept.Interceptable
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.interceptors.interaction.impl.ViewInteractionInterceptor
import com.kaspersky.kaspresso.interceptors.interactors.ViewInteractor
import com.kaspersky.kaspresso.proxy.ViewActionProxy
import com.kaspersky.kaspresso.proxy.ViewAssertionProxy

abstract class Composer(
    internal val configurator: Configurator
) {
    fun <T> T.compose(vararg actions: T.() -> Unit)
            where T : BaseActions, T : BaseAssertions, T : Interceptable<ViewInteraction, ViewAssertion, ViewAction> {

        val viewInteraction: ViewInteraction = getViewInteraction(actions.first())

        intercept {
            onCheck(true) { viewInteraction: ViewInteraction, viewAssertion: ViewAssertion ->
                viewInteraction.check(ViewAssertionProxy(viewAssertion, configurator.viewAssertionInterceptors))
            }
            onPerform(true) { viewInteraction: ViewInteraction, viewAction: ViewAction ->
                viewInteraction.perform(ViewActionProxy(viewAction, configurator.viewActionInterceptors))
            }
        }

        configurator.viewInteractors.fold(
            initial = {
                Configurator.logger.i("Composed action started.")
                var cachedError: Throwable? = null

                actions.forEach { action: T.() -> Unit ->
                    try {
                        action.invoke(this@compose)
                        Configurator.logger.i("Composed action successfully performed.")
                        return@fold
                    } catch (error: Throwable) {
                        cachedError = error
                        Configurator.logger.i("One part of composed action failed.")
                    }
                }

                Configurator.logger.i("Composed action totally failed.")
                throw cachedError!!
            },
            operation = { acc, viewInteractor: ViewInteractor -> { viewInteractor.interact(viewInteraction, acc) } }
        ).invoke()

        setOldViewInteractionInterceptor()
    }

    private fun <T> T.getViewInteraction(action: T.() -> Unit): ViewInteraction
            where T : BaseActions, T : BaseAssertions, T : Interceptable<ViewInteraction, ViewAssertion, ViewAction> {

        var cachedViewInteraction: ViewInteraction? = null

        val cacheViewInteraction: (ViewInteraction, Any?) -> Unit =
            { viewInteraction: ViewInteraction, _ -> cachedViewInteraction = viewInteraction }

        intercept {
            onCheck(true, cacheViewInteraction)
            onPerform(true, cacheViewInteraction)
        }

        action.invoke(this)

        return cachedViewInteraction!!
    }

    private fun <T> T.setOldViewInteractionInterceptor()
            where T : BaseActions, T : BaseAssertions, T : Interceptable<ViewInteraction, ViewAssertion, ViewAction> {

        val oldInterceptor = ViewInteractionInterceptor(configurator)

        intercept {
            onCheck(true, oldInterceptor::interceptCheck)
            onPerform(true, oldInterceptor::interceptPerform)
        }
    }
}