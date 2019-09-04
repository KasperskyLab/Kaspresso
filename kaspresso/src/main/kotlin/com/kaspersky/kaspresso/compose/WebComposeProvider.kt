package com.kaspersky.kaspresso.compose

import com.agoda.kakao.web.WebElementBuilder
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.failure.FailureLoggingProvider
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider
import com.kaspersky.kaspresso.interceptors.interaction.impl.WebInteractionInterceptor
import com.kaspersky.kaspresso.interceptors.interaction.impl.compose.ComposeWebInteractionInterceptor
import com.kaspersky.kaspresso.interceptors.interactors.impl.failure.FailureLoggingWebInteractor
import com.kaspersky.kaspresso.interceptors.interactors.impl.flakysafety.FlakySafeWebInteractor

interface WebComposeProvider {

    val configurator: Configurator

    private val flakySafetyProvider: FlakySafetyProvider?
        get() = configurator.webInteractors.find { it is FlakySafeWebInteractor } as FlakySafetyProvider?

    private val failureLoggingProvider: FailureLoggingProvider?
        get() = configurator.webInteractors.find { it is FailureLoggingWebInteractor } as FailureLoggingProvider?

    fun WebElementBuilder.webCompose(
        block: WebComponentPack.() -> Unit
    ) {
        val webComponents: List<WebComponent> = WebComponentPack(this).apply(block).build()

        webComponents.forEach { it.webElementBuilder.setComposeInterception() }

        val composedAction: () -> Unit = {
            flakySafetyProvider
                ?.flakySafely { invokeComposed(webComponents) }
                ?: invokeComposed(webComponents)
        }

        failureLoggingProvider
            ?.withLoggingOnFailure(composedAction)
            ?: composedAction.invoke()

        webComponents.forEach { it.webElementBuilder.setInterception() }
    }

    fun WebElementBuilder.KWebInteraction.webCompose(
        webElementBuilder: WebElementBuilder,
        block: ActionsPack<WebElementBuilder.KWebInteraction>.() -> Unit
    ) {
        val actions: List<WebElementBuilder.KWebInteraction.() -> Unit> =
            ActionsPack<WebElementBuilder.KWebInteraction>().apply(block).build()

        webElementBuilder.setComposeInterception()

        val composedAction: () -> Unit = {
            flakySafetyProvider
                ?.flakySafely { invokeComposed(this, actions) }
                ?: invokeComposed(this, actions)
        }

        failureLoggingProvider
            ?.withLoggingOnFailure(composedAction)
            ?: composedAction.invoke()

        webElementBuilder.setInterception()
    }

    private fun WebElementBuilder.setComposeInterception() {
        val interceptor = ComposeWebInteractionInterceptor(configurator)

        intercept {
            onCheck(true, interceptor::interceptCheck)
            onPerform(true, interceptor::interceptPerform)
        }
    }

    private fun WebElementBuilder.setInterception() {
        val interceptor = WebInteractionInterceptor(configurator)

        intercept {
            onCheck(true, interceptor::interceptCheck)
            onPerform(true, interceptor::interceptPerform)
        }
    }
}