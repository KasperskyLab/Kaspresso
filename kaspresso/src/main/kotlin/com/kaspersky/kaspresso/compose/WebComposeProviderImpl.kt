package com.kaspersky.kaspresso.compose

import com.agoda.kakao.web.WebElementBuilder
import com.kaspersky.kaspresso.compose.Composer.getUnitedComposedAction
import com.kaspersky.kaspresso.compose.pack.ActionsOnWebElementsPack
import com.kaspersky.kaspresso.compose.pack.ActionsPack
import com.kaspersky.kaspresso.failure.FailureLoggingProvider
import com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderGlobalImpl
import com.kaspersky.kaspresso.kaspresso.Kaspresso

/**
 * The implementation of the [WebComposeProvider] interface.
 */
class WebComposeProviderImpl(
    private val kaspresso: Kaspresso
) : WebComposeProvider {

    private val failureLoggingProvider: FailureLoggingProvider =
        FailureLoggingProviderImpl(kaspresso.libLogger)
    private val flakySafetyProvider: FlakySafetyProvider =
        FlakySafetyProviderGlobalImpl(kaspresso)

    /**
     * Composes a [block] of actions with their web views to invoke on in one composite action that succeeds if at least
     * one of it's parts succeeds.
     *
     * @param block the actions to compose.
     */
    override fun WebElementBuilder.compose(block: ActionsOnWebElementsPack.() -> Unit) {
        val checks: List<() -> Unit> = ActionsOnWebElementsPack(this).apply(block).build()
        invokeWebComposed(checks)
    }

    /**
     * Composes a [block] of actions on the given web view in one composite action that succeeds if at least
     * one of it's parts succeeds.
     *
     * @param block the actions to compose.
     */
    override fun WebElementBuilder.KWebInteraction.compose(
        webElementBuilder: WebElementBuilder,
        block: ActionsPack<WebElementBuilder.KWebInteraction>.() -> Unit
    ) {
        val checks: List<() -> Unit> = ActionsPack(this).apply(block).build()
        invokeWebComposed(checks)
    }

    private fun invokeWebComposed(checks: List<() -> Unit>) {
        val unitedComposedAction = getUnitedComposedAction(checks, kaspresso.libLogger)

        failureLoggingProvider.withLoggingOnFailure {
            flakySafetyProvider.flakySafely(
                unitedComposedAction
            )
        }
    }
}