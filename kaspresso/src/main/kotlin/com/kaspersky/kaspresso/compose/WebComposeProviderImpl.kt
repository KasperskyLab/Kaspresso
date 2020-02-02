package com.kaspersky.kaspresso.compose

import com.agoda.kakao.web.WebElementBuilder
import com.kaspersky.kaspresso.compose.pack.ActionsOnWebElementsPack
import com.kaspersky.kaspresso.compose.pack.ActionsPack
import com.kaspersky.kaspresso.failure.FailureLoggingProvider
import com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderImpl
import com.kaspersky.kaspresso.flakysafety.scalpel.FlakySafeInterceptorScalpel.restoreFlakySafeInterceptorToLibs
import com.kaspersky.kaspresso.flakysafety.scalpel.FlakySafeInterceptorScalpel.scalpFlakySafeInterceptorFromLibs
import com.kaspersky.kaspresso.kaspresso.Kaspresso

/**
 * The implementation of the [WebComposeProvider] interface.
 */
class WebComposeProviderImpl(
    private val kaspresso: Kaspresso
) : WebComposeProvider {

    private val failureLoggingProvider: FailureLoggingProvider = FailureLoggingProviderImpl(kaspresso.libLogger)

    /**
     * Composes a [block] of actions with their web views to invoke on in one composite action that succeeds if at least
     * one of it's parts succeeds.
     *
     * @param block the actions to compose.
     */
    override fun WebElementBuilder.compose(timeoutMs: Long?, block: ActionsOnWebElementsPack.() -> Unit) {
        val actions: List<() -> Unit> = ActionsOnWebElementsPack(this).apply(block).build()
        invokeWebComposed(actions = actions)
    }

    /**
     * Composes a [block] of actions on the given web view in one composite action that succeeds if at least
     * one of it's parts succeeds.
     *
     * @param block the actions to compose.
     */
    override fun WebElementBuilder.KWebInteraction.compose(
        timeoutMs: Long?,
        webElementBuilder: WebElementBuilder,
        block: ActionsPack<WebElementBuilder.KWebInteraction>.() -> Unit
    ) {
        val actions: List<() -> Unit> = ActionsPack(this).apply(block).build()
        invokeWebComposed(actions = actions)
    }

    private fun invokeWebComposed(timeoutMs: Long? = null, actions: List<() -> Unit>) {
        scalpFlakySafeInterceptorFromLibs(kaspresso)

        val flakySafetyParams = kaspresso.params.flakySafetyParams
        flakySafetyParams.timeoutMs = timeoutMs ?: flakySafetyParams.timeoutMs
        invokeComposed(
            actions = actions,
            logger = kaspresso.libLogger,
            failureLoggingProvider = failureLoggingProvider,
            flakySafetyProvider = FlakySafetyProviderImpl(
                flakySafetyParams,
                kaspresso.libLogger
            )
        )

        restoreFlakySafeInterceptorToLibs(kaspresso)
    }
}