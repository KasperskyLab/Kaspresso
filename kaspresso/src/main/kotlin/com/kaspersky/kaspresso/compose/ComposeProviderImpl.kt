package com.kaspersky.kaspresso.compose

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import com.agoda.kakao.common.actions.BaseActions
import com.agoda.kakao.common.assertions.BaseAssertions
import com.agoda.kakao.intercept.Interceptable
import com.kaspersky.components.kautomator.component.common.actions.UiBaseActions
import com.kaspersky.components.kautomator.component.common.assertions.UiBaseAssertions
import com.kaspersky.components.kautomator.intercept.base.UiInterceptable
import com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAssertion
import com.kaspersky.kaspresso.compose.Composer.getUnitedComposedAction
import com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack
import com.kaspersky.kaspresso.compose.pack.ActionsPack
import com.kaspersky.kaspresso.failure.FailureLoggingProvider
import com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderGlobalImpl
import com.kaspersky.kaspresso.kaspresso.Kaspresso

/**
 * The implementation of the [ComposeProvider] interface.
 */
class ComposeProviderImpl(
    private val kaspresso: Kaspresso
) : ComposeProvider {

    private val failureLoggingProvider: FailureLoggingProvider =
        FailureLoggingProviderImpl(kaspresso.libLogger)
    private val flakySafetyProvider: FlakySafetyProvider =
        FlakySafetyProviderGlobalImpl(kaspresso)

    override fun compose(
        timeoutMs: Long?,
        intervalMs: Long?,
        allowedExceptions: Set<Class<out Throwable>>?,
        block: ActionsOnElementsPack.() -> Unit
    ) {
        val composeBranches = ActionsOnElementsPack().apply(block).build()
        val composeChecks = composeBranches.map { it.check }
        val unitedComposedAction = getUnitedComposedAction(composeChecks, kaspresso.libLogger)

        val succeedBranchOrderNumber = failureLoggingProvider.withLoggingOnFailure {
            flakySafetyProvider.flakySafely(
                timeoutMs = timeoutMs,
                intervalMs = intervalMs,
                allowedExceptions = allowedExceptions,
                action = unitedComposedAction
            )
        }

        // execution `then` section of a success branch
        composeBranches[succeedBranchOrderNumber].postAction?.invoke()
    }

    override fun unsafeCompose(block: ActionsOnElementsPack.() -> Unit) {
        val composeBranches = ActionsOnElementsPack().apply(block).build()
        val composeChecks = composeBranches.map { it.check }
        val unitedComposedAction = getUnitedComposedAction(composeChecks, kaspresso.libLogger)

        val succeedBranchOrderNumber = unitedComposedAction.invoke()

        // execution `then` section of a success branch
        composeBranches[succeedBranchOrderNumber].postAction?.invoke()
    }

    override fun <Type> Type.compose(
        timeoutMs: Long?,
        intervalMs: Long?,
        allowedExceptions: Set<Class<out Throwable>>?,
        block: ActionsPack<Type>.() -> Unit
    ) where Type : BaseActions, Type : BaseAssertions,
            Type : Interceptable<ViewInteraction, ViewAssertion, ViewAction> {
        val checks: List<() -> Unit> = ActionsPack(this).apply(block).build()
        invokeViewComposeByProviders(timeoutMs, intervalMs, allowedExceptions, checks)
    }

    override fun <Type> Type.unsafeCompose(block: ActionsPack<Type>.() -> Unit)
            where Type : BaseActions, Type : BaseAssertions,
                  Type : Interceptable<ViewInteraction, ViewAssertion, ViewAction> {
        invokeViewComposeUnsafely(
            checks = ActionsPack(this).apply(block).build()
        )
    }

    override fun <Type> Type.compose(
        timeoutMs: Long?,
        intervalMs: Long?,
        allowedExceptions: Set<Class<out Throwable>>?,
        block: ActionsPack<Type>.() -> Unit
    ) where Type : UiBaseActions, Type : UiBaseAssertions,
            Type : UiInterceptable<UiObjectInteraction, UiObjectAssertion, UiObjectAction> {
        val checks: List<() -> Unit> = ActionsPack(this).apply(block).build()
        invokeViewComposeByProviders(timeoutMs, intervalMs, allowedExceptions, checks)
    }

    override fun <Type> Type.unsafeCompose(block: ActionsPack<Type>.() -> Unit)
            where Type : UiBaseActions, Type : UiBaseAssertions,
                  Type : UiInterceptable<UiObjectInteraction, UiObjectAssertion, UiObjectAction> {
        invokeViewComposeUnsafely(
            checks = ActionsPack(this).apply(block).build()
        )
    }

    private fun invokeViewComposeByProviders(
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

    private fun invokeViewComposeUnsafely(checks: List<() -> Unit>) {
        val unitedComposedAction = getUnitedComposedAction(checks, kaspresso.libLogger)
        unitedComposedAction.invoke()
    }
}