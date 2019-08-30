package com.kaspersky.kaspresso.testcases.core.testcontext

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.web.assertion.WebAssertion
import androidx.test.espresso.web.assertion.WebAssertionProxy
import androidx.test.espresso.web.model.Atom
import androidx.test.espresso.web.sugar.Web
import com.agoda.kakao.common.actions.BaseActions
import com.agoda.kakao.common.assertions.BaseAssertions
import com.agoda.kakao.intercept.Interceptable
import com.agoda.kakao.web.WebActions
import com.agoda.kakao.web.WebAssertions
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.extensions.espressoext.getMatcher
import com.kaspersky.kaspresso.interceptors.interaction.impl.ViewInteractionInterceptor
import com.kaspersky.kaspresso.interceptors.interaction.impl.WebInteractionInterceptor
import com.kaspersky.kaspresso.interceptors.interactors.ViewInteractor
import com.kaspersky.kaspresso.interceptors.interactors.WebInteractor
import com.kaspersky.kaspresso.proxy.AtomProxy
import com.kaspersky.kaspresso.proxy.ViewActionProxy
import com.kaspersky.kaspresso.proxy.ViewAssertionProxy

abstract class Composer(
    internal val configurator: Configurator
) {
    fun <T> T.compose(vararg actions: T.() -> Unit): Unit
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

    fun <T> T.compose(
        interceptable: Interceptable<Web.WebInteraction<*>, WebAssertion<*>, Atom<*>>,
        vararg actions: T.() -> Unit
    ): Unit where T : WebActions, T : WebAssertions {

        val webInteraction: Web.WebInteraction<*> = getWebInteraction(interceptable, actions.first())

        interceptable.intercept {
            onCheck(true) { webInteraction: Web.WebInteraction<*>, webAssertion: WebAssertion<*> ->
                webInteraction.check(
                    WebAssertionProxy(
                        webAssertion,
                        webInteraction.getMatcher(),
                        configurator.webAssertionInterceptors
                    )
                )
            }
            onPerform(true) { webInteraction: Web.WebInteraction<*>, atom: Atom<*> ->
                webInteraction.perform(AtomProxy(atom, webInteraction.getMatcher(), configurator.atomInterceptors))
            }
        }

        configurator.webInteractors.fold(
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
            operation = { acc, webInteractor: WebInteractor -> { webInteractor.interact(webInteraction, acc) } }
        ).invoke()

        setOldWebInteractionInterceptor(interceptable)
    }

    private fun <T> T.getViewInteraction(action: T.() -> Unit): ViewInteraction
            where T : BaseActions, T : BaseAssertions, T : Interceptable<ViewInteraction, ViewAssertion, ViewAction> {

        var cachedViewInteraction: ViewInteraction? = null

        intercept {
            onAll(true) { viewInteraction: ViewInteraction -> cachedViewInteraction = viewInteraction }
        }

        action.invoke(this)

        return cachedViewInteraction!!
    }

    private fun <T> T.getWebInteraction(
        interceptable: Interceptable<Web.WebInteraction<*>, WebAssertion<*>, Atom<*>>,
        action: T.() -> Unit
    ): Web.WebInteraction<*> where T : WebActions, T : WebAssertions {

        var cachedWebInteraction: Web.WebInteraction<*>? = null

        interceptable.intercept {
            onAll(true) { webInteraction: Web.WebInteraction<*> -> cachedWebInteraction = webInteraction }
        }

        action.invoke(this)

        return cachedWebInteraction!!
    }

    private fun <T> T.setOldViewInteractionInterceptor(): Unit
            where T : BaseActions, T : BaseAssertions, T : Interceptable<ViewInteraction, ViewAssertion, ViewAction> {

        val oldInterceptor = ViewInteractionInterceptor(configurator)

        intercept {
            onCheck(true, oldInterceptor::interceptCheck)
            onPerform(true, oldInterceptor::interceptPerform)
        }
    }

    private fun <T> T.setOldWebInteractionInterceptor(
        interceptable: Interceptable<Web.WebInteraction<*>, WebAssertion<*>, Atom<*>>
    ): Unit where T : WebActions, T : WebAssertions {

        val oldInterceptor = WebInteractionInterceptor(configurator)

        interceptable.intercept {
            onCheck(true, oldInterceptor::interceptCheck)
            onPerform(true, oldInterceptor::interceptPerform)
        }
    }
}