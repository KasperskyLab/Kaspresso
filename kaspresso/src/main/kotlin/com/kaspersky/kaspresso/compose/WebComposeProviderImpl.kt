package com.kaspersky.kaspresso.compose

import com.agoda.kakao.web.WebElementBuilder
import com.kaspersky.kaspresso.compose.pack.ActionsOnWebElementsPack
import com.kaspersky.kaspresso.compose.pack.ActionsPack
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.failure.FailureLoggingProvider
import com.kaspersky.kaspresso.failure.withLoggingOnFailureIfNotNull
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider
import com.kaspersky.kaspresso.flakysafety.flakySafelyIfNotNull
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.failure.FailureLoggingWebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeWebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.tokakao.compose.ComposeKakaoWebInterceptor
import com.kaspersky.kaspresso.interceptors.tokakao.impl.KakaoWebInterceptor

class WebComposeProviderImpl(
    private val kaspresso: Kaspresso
) : WebComposeProvider {

    override fun WebElementBuilder.compose(block: ActionsOnWebElementsPack.() -> Unit) {
        val actions: List<() -> Unit> = ActionsOnWebElementsPack(this).apply(block).build()

        setComposeInterception()

        val (flakySafetyProvider, failureLoggingProvider) = getProviders()

        failureLoggingProvider.withLoggingOnFailureIfNotNull {
            flakySafetyProvider.flakySafelyIfNotNull {
                invokeComposed(actions, kaspresso.libLogger)
            }
        }

        setInterception()
    }

    override fun WebElementBuilder.KWebInteraction.compose(
        webElementBuilder: WebElementBuilder,
        block: ActionsPack<WebElementBuilder.KWebInteraction>.() -> Unit
    ) {
        val actions: List<() -> Unit> = ActionsPack(this).apply(block).build()

        webElementBuilder.setComposeInterception()

        val (flakySafetyProvider, failureLoggingProvider) = getProviders()

        failureLoggingProvider.withLoggingOnFailureIfNotNull {
            flakySafetyProvider.flakySafelyIfNotNull {
                invokeComposed(actions, kaspresso.libLogger)
            }
        }

        webElementBuilder.setInterception()
    }

    private fun getProviders(): Pair<FlakySafetyProvider?, FailureLoggingProvider?> {
        var flakySafetyProvider: FlakySafetyProvider? = null
        var failureLoggingProvider: FailureLoggingProvider? = null

        kaspresso.webBehaviorInterceptors.forEach { webBehaviorInterceptor: WebBehaviorInterceptor ->
            when (webBehaviorInterceptor) {
                is FlakySafeWebBehaviorInterceptor -> flakySafetyProvider = webBehaviorInterceptor
                is FailureLoggingWebBehaviorInterceptor -> failureLoggingProvider = webBehaviorInterceptor
            }
        }

        return Pair(flakySafetyProvider, failureLoggingProvider)
    }

    private fun WebElementBuilder.setComposeInterception() {
        val interceptor = ComposeKakaoWebInterceptor(kaspresso)

        intercept {
            onCheck(true, interceptor::interceptCheck)
            onPerform(true, interceptor::interceptPerform)
        }
    }

    private fun WebElementBuilder.setInterception() {
        val interceptor = KakaoWebInterceptor(kaspresso)

        intercept {
            onCheck(true, interceptor::interceptCheck)
            onPerform(true, interceptor::interceptPerform)
        }
    }
}