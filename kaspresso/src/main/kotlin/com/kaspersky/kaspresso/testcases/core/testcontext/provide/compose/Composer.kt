package com.kaspersky.kaspresso.testcases.core.testcontext.provide.compose

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

class Composer(private val configurator: Configurator) {

    fun <T> compose(block: ComponentPack<T>.() -> Unit): Unit
            where T : BaseActions, T : BaseAssertions, T : Interceptable<ViewInteraction, ViewAssertion, ViewAction> {

        val components: List<Component<T>> = ComponentPack<T>().apply(block).build()

        val firstComponent: Component<T> = components.first()
        val otherCompoentns: List<Component<T>> = components.subList(1, components.size)

        otherCompoentns.forEach { component: Component<T> ->

            component.element.intercept {
                onCheck(true) { viewInteraction: ViewInteraction, viewAssertion: ViewAssertion ->
                    viewInteraction.check(ViewAssertionProxy(viewAssertion, configurator.viewAssertionInterceptors))
                }
                onPerform(true) { viewInteraction: ViewInteraction, viewAction: ViewAction ->
                    viewInteraction.perform(ViewActionProxy(viewAction, configurator.viewActionInterceptors))
                }
            }
        }

        firstComponent.element.intercept {
            onAll(true) { viewInteraction: ViewInteraction ->

                firstComponent.element.intercept {
                    onCheck(true) { viewInteraction: ViewInteraction, viewAssertion: ViewAssertion ->
                        viewInteraction.check(ViewAssertionProxy(viewAssertion, configurator.viewAssertionInterceptors))
                    }
                    onPerform(true) { viewInteraction: ViewInteraction, viewAction: ViewAction ->
                        viewInteraction.perform(ViewActionProxy(viewAction, configurator.viewActionInterceptors))
                    }
                }

                configurator.viewInteractors.fold(
                    initial = { invokeComposed(components) },
                    operation = { acc, viewInteractor: ViewInteractor ->
                        { viewInteractor.interact(viewInteraction, acc) }
                    }
                ).invoke()
            }
        }

        firstComponent.action.invoke(firstComponent.element)

        components.forEach { setOldViewInteractionInterceptor(it.element) }
    }

    fun <T> compose(element: T, block: ActionsPack<T>.() -> Unit): Unit
            where T : BaseActions, T : BaseAssertions, T : Interceptable<ViewInteraction, ViewAssertion, ViewAction> {

        val actions: List<T.() -> Unit> = ActionsPack<T>().apply(block).build()

        element.intercept {
            onAll(true) { viewInteraction: ViewInteraction ->

                element.intercept {
                    onCheck(true) { viewInteraction: ViewInteraction, viewAssertion: ViewAssertion ->
                        viewInteraction.check(ViewAssertionProxy(viewAssertion, configurator.viewAssertionInterceptors))
                    }
                    onPerform(true) { viewInteraction: ViewInteraction, viewAction: ViewAction ->
                        viewInteraction.perform(ViewActionProxy(viewAction, configurator.viewActionInterceptors))
                    }
                }

                configurator.viewInteractors.fold(
                    initial = { invokeComposed(element, actions) },
                    operation = { acc, viewInteractor: ViewInteractor ->
                        { viewInteractor.interact(viewInteraction, acc) }
                    }
                ).invoke()
            }
        }

        actions.first().invoke(element)

        setOldViewInteractionInterceptor(element)
    }

    private fun <T> setOldViewInteractionInterceptor(element: T): Unit
            where T : BaseActions, T : BaseAssertions, T : Interceptable<ViewInteraction, ViewAssertion, ViewAction> {

        val oldInterceptor = ViewInteractionInterceptor(configurator)

        element.intercept {
            onCheck(true, oldInterceptor::interceptCheck)
            onPerform(true, oldInterceptor::interceptPerform)
        }
    }
}