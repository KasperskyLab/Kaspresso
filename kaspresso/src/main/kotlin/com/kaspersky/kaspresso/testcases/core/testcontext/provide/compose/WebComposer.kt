package com.kaspersky.kaspresso.testcases.core.testcontext.provide.compose

import androidx.test.espresso.web.assertion.WebAssertion
import androidx.test.espresso.web.assertion.WebAssertionProxy
import androidx.test.espresso.web.model.Atom
import androidx.test.espresso.web.sugar.Web
import com.agoda.kakao.intercept.Interceptable
import com.agoda.kakao.web.WebActions
import com.agoda.kakao.web.WebAssertions
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.extensions.espressoext.getMatcher
import com.kaspersky.kaspresso.interceptors.interaction.impl.WebInteractionInterceptor
import com.kaspersky.kaspresso.interceptors.interactors.WebInteractor
import com.kaspersky.kaspresso.proxy.AtomProxy

class WebComposer(private val configurator: Configurator) {

    fun <T> compose(block: WebComponentPack<T>.() -> Unit): Unit where T : WebActions, T : WebAssertions {

        val webComponents: List<WebComponent<T>> = WebComponentPack<T>().apply(block).build()

        val firstWebComponent: WebComponent<T> = webComponents.first()
        val otherWebCompoentns: List<WebComponent<T>> = webComponents.subList(1, webComponents.size)

        otherWebCompoentns.forEach { webComponent: WebComponent<T> ->

            webComponent.interceptable.intercept {
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
        }

        firstWebComponent.interceptable.intercept {
            onAll(true) { webInteraction: Web.WebInteraction<*> ->

                firstWebComponent.interceptable.intercept {
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
                    initial = { invokeComposed(webComponents) },
                    operation = { acc, webInteractor: WebInteractor -> { webInteractor.interact(webInteraction, acc) } }
                ).invoke()
            }
        }

        firstWebComponent.action.invoke(firstWebComponent.element)

        webComponents.forEach { setOldWebInteractionInterceptor(it.interceptable) }
    }

    fun <T> compose(
        element: T,
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
                    initial = { invokeComposed(element, actions) },
                    operation = { acc, webInteractor: WebInteractor -> { webInteractor.interact(webInteraction, acc) } }
                ).invoke()
            }
        }

        actions.first().invoke(element)

        setOldWebInteractionInterceptor(interceptable)
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
}