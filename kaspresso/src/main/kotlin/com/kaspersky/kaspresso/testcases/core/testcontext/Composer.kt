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
import java.lang.IllegalArgumentException

abstract class Composer(
    internal val configurator: Configurator
) {
    fun <T> T.compose(block: ActionsPack<T>.() -> Unit): Unit
            where T : BaseActions, T : BaseAssertions, T : Interceptable<ViewInteraction, ViewAssertion, ViewAction> {

        val actions: List<T.() -> Unit> = ActionsPack<T>().apply(block).build()

        intercept {
            onAll(true) { viewInteraction: ViewInteraction ->

                intercept {
                    onCheck(true) { viewInteraction: ViewInteraction, viewAssertion: ViewAssertion ->
                        viewInteraction.check(
                            ViewAssertionProxy(viewAssertion, configurator.viewAssertionInterceptors)
                        )
                    }
                    onPerform(true) { viewInteraction: ViewInteraction, viewAction: ViewAction ->
                        viewInteraction.perform(
                            ViewActionProxy(viewAction, configurator.viewActionInterceptors)
                        )
                    }
                }

                configurator.viewInteractors.fold(
                    initial = { invokeComposed(this@compose, actions) },
                    operation = { acc, viewInteractor: ViewInteractor ->
                        { viewInteractor.interact(viewInteraction, acc) }
                    }
                ).invoke()
            }
        }

        actions.first().invoke(this)

        setOldViewInteractionInterceptor()
    }

    fun <T> T.compose(
        interceptable: Interceptable<Web.WebInteraction<*>, WebAssertion<*>, Atom<*>>,
        block: ActionsPack<T>.() -> Unit
    ): Unit where T : WebActions, T : WebAssertions {

        val actions: List<T.() -> Unit> = ActionsPack<T>().apply(block).build()

        interceptable.intercept {
            onAll(true) { webInteraction: Web.WebInteraction<*> ->

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
                        webInteraction.perform(
                            AtomProxy(atom, webInteraction.getMatcher(), configurator.atomInterceptors)
                        )
                    }
                }

                configurator.webInteractors.fold(
                    initial = { invokeComposed(this@compose, actions) },
                    operation = { acc, webInteractor: WebInteractor -> { webInteractor.interact(webInteraction, acc) } }
                ).invoke()
            }
        }

        actions.first().invoke(this)

        setOldWebInteractionInterceptor(interceptable)
    }

    private fun <T> T.setOldViewInteractionInterceptor(): Unit
            where T : BaseActions, T : BaseAssertions, T : Interceptable<ViewInteraction, ViewAssertion, ViewAction> {

        val oldInterceptor = ViewInteractionInterceptor(configurator)

        intercept {
            onCheck(true, oldInterceptor::interceptCheck)
            onPerform(true, oldInterceptor::interceptPerform)
        }
    }

    private fun setOldWebInteractionInterceptor(
        interceptable: Interceptable<Web.WebInteraction<*>, WebAssertion<*>, Atom<*>>
    ) {
        val oldInterceptor = WebInteractionInterceptor(configurator)

        interceptable.intercept {
            onCheck(true, oldInterceptor::interceptCheck)
            onPerform(true, oldInterceptor::interceptPerform)
        }
    }

    private fun <T> invokeComposed(context: T, actions: List<T.() -> Unit>) {
        Configurator.logger.i("Composed action started.")
        var cachedError: Throwable? = null

        actions.forEach { action: T.() -> Unit ->
            try {
                action.invoke(context)
                Configurator.logger.i("Composed action successfully performed.")
                return
            } catch (error: Throwable) {
                cachedError = error
                Configurator.logger.i("One part of composed action failed.")
            }
        }

        Configurator.logger.i("Composed action totally failed.")
        throw cachedError!!
    }

    class ActionsPack<T> {

        private val actions: MutableList<T.() -> Unit> = arrayListOf()

        fun or(action: T.() -> Unit) {
            actions += action
        }

        internal fun build(): MutableList<T.() -> Unit> {
            if (actions.isEmpty()) throw IllegalArgumentException("Nothing to compose")
            return actions
        }
    }
}