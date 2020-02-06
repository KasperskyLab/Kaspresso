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

    override fun WebElementBuilder.compose(
        timeoutMs: Long?,
        intervalMs: Long?,
        allowedExceptions: Set<Class<out Throwable>>?,
        block: ActionsOnWebElementsPack.() -> Unit
    ) {
        val checks: List<() -> Unit> = ActionsOnWebElementsPack(this).apply(block).build()
        invokeWebComposeByProviders(timeoutMs, intervalMs, allowedExceptions, checks)
    }

    override fun WebElementBuilder.unsafeCompose(block: ActionsOnWebElementsPack.() -> Unit) {
        invokeWebComposeUnsafely(
            checks = ActionsOnWebElementsPack(this).apply(block).build()
        )
    }

    override fun WebElementBuilder.KWebInteraction.compose(
        webElementBuilder: WebElementBuilder,
        timeoutMs: Long?,
        intervalMs: Long?,
        allowedExceptions: Set<Class<out Throwable>>?,
        block: ActionsPack<WebElementBuilder.KWebInteraction>.() -> Unit
    ) {
        val checks: List<() -> Unit> = ActionsPack(this).apply(block).build()
        invokeWebComposeByProviders(timeoutMs, intervalMs, allowedExceptions, checks)
    }

    override fun WebElementBuilder.KWebInteraction.unsafeCompose(
        webElementBuilder: WebElementBuilder,
        block: ActionsPack<WebElementBuilder.KWebInteraction>.() -> Unit
    ) {
        invokeWebComposeUnsafely(
            checks = ActionsPack(this).apply(block).build()
        )
    }

    private fun invokeWebComposeByProviders(
        timeoutMs: Long?,
        intervalMs: Long?,
        allowedExceptions: Set<Class<out Throwable>>?,
        checks: List<() -> Unit>
    ) {
        val unitedComposedAction = getUnitedComposedAction(checks, kaspresso.libLogger)

        failureLoggingProvider.withLoggingOnFailure {
            flakySafetyProvider.flakySafely(
                timeoutMs = timeoutMs,
                intervalMs = intervalMs,
                allowedExceptions = allowedExceptions,
                action = unitedComposedAction
            )
        }
    }

    private fun invokeWebComposeUnsafely(checks: List<() -> Unit>) {
        val unitedComposedAction = getUnitedComposedAction(checks, kaspresso.libLogger)
        unitedComposedAction.invoke()
    }
}