package com.kaspersky.kaspresso.compose

import com.agoda.kakao.web.WebElementBuilder
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.failure.FailureLoggingProvider
import com.kaspersky.kaspresso.failure.withLoggingOnFailureIfNotNull
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider
import com.kaspersky.kaspresso.flakysafety.flakySafelyIfNotNull
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.failure.FailureLoggingWebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeWebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.tokakao.compose.ComposeKakaoWebInterceptor
import com.kaspersky.kaspresso.interceptors.tokakao.impl.KakaoWebInterceptor

interface WebComposeProvider {

    val configurator: Configurator

    fun WebElementBuilder.webCompose(block: WebComponentPack.() -> Unit) {
        val webComponents: List<WebComponent> = WebComponentPack(this).apply(block).build()

        webComponents.forEach { it.webElementBuilder.setComposeInterception() }

        val (flakySafetyProvider, failureLoggingProvider) = getProviders()

        failureLoggingProvider.withLoggingOnFailureIfNotNull {
            flakySafetyProvider.flakySafelyIfNotNull {
                invokeComposed(webComponents)
            }
        }

        webComponents.forEach { it.webElementBuilder.setInterception() }
    }

    fun WebElementBuilder.KWebInteraction.webCompose(
        webElementBuilder: WebElementBuilder,
        block: ActionsPack<WebElementBuilder.KWebInteraction>.() -> Unit
    ) {
        val actions: List<WebElementBuilder.KWebInteraction.() -> Unit> =
            ActionsPack<WebElementBuilder.KWebInteraction>().apply(block).build()

        webElementBuilder.setComposeInterception()

        val (flakySafetyProvider, failureLoggingProvider) = getProviders()

        failureLoggingProvider.withLoggingOnFailureIfNotNull {
            flakySafetyProvider.flakySafelyIfNotNull {
                invokeComposed(this, actions)
            }
        }

        webElementBuilder.setInterception()
    }

    private fun getProviders(): Pair<FlakySafetyProvider?, FailureLoggingProvider?> {
        var flakySafetyProvider: FlakySafetyProvider? = null
        var failureLoggingProvider: FailureLoggingProvider? = null

        configurator.webBehaviorInterceptors.forEach { webBehaviorInterceptor: WebBehaviorInterceptor ->
            when (webBehaviorInterceptor) {
                is FlakySafeWebBehaviorInterceptor -> flakySafetyProvider = webBehaviorInterceptor
                is FailureLoggingWebBehaviorInterceptor -> failureLoggingProvider = webBehaviorInterceptor
            }
        }

        return Pair(flakySafetyProvider, failureLoggingProvider)
    }

    private fun WebElementBuilder.setComposeInterception() {
        val interceptor = ComposeKakaoWebInterceptor(configurator)

        intercept {
            onCheck(true, interceptor::interceptCheck)
            onPerform(true, interceptor::interceptPerform)
        }
    }

    private fun WebElementBuilder.setInterception() {
        val interceptor = KakaoWebInterceptor(configurator)

        intercept {
            onCheck(true, interceptor::interceptCheck)
            onPerform(true, interceptor::interceptPerform)
        }
    }
}