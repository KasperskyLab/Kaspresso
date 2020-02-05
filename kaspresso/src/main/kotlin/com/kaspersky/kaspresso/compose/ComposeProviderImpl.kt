package com.kaspersky.kaspresso.compose

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import com.agoda.kakao.common.actions.BaseActions
import com.agoda.kakao.common.assertions.BaseAssertions
import com.agoda.kakao.intercept.Interceptable
import com.kaspersky.components.kautomator.dsl.common.actions.UiBaseActions
import com.kaspersky.components.kautomator.dsl.common.assertions.UiBaseAssertions
import com.kaspersky.components.kautomator.intercepting.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercepting.intercept.UiInterceptable
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAssertion
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

    /**
     * Composes a [block] of actions with their views to invoke on in one composite action that succeeds if at least
     * one of it's parts succeeds.
     * Allows to use in one compose block views from Kakao and Kautomator simultaneously
     *
     * @param block the actions to compose.
     */
    override fun compose(block: ActionsOnElementsPack.() -> Unit) {
        val composeBranches = ActionsOnElementsPack().apply(block).build()
        val composeChecks = composeBranches.map { it.check }
        val unitedComposedAction = getUnitedComposedAction(composeChecks, kaspresso.libLogger)

        val succeedBranchOrderNumber = failureLoggingProvider.withLoggingOnFailure {
            flakySafetyProvider.flakySafely(
                unitedComposedAction
            )
        }

        // execution `then` section of a success branch
        composeBranches[succeedBranchOrderNumber].postAction?.invoke()
    }

    /**
     * Composes a [block] of actions on the given view of type [T] in one composite action that succeeds if at least
     * one of it's parts succeeds.
     *
     * @param block the actions to compose.
     */
    override fun <Type> Type.compose(block: ActionsPack<Type>.() -> Unit)
            where Type : BaseActions, Type : BaseAssertions,
                  Type : Interceptable<ViewInteraction, ViewAssertion, ViewAction> {
        val checks: List<() -> Unit> = ActionsPack(this).apply(block).build()
        invokeViewCompose(checks)
    }

    /**
     * Composes a [block] of actions on the given view of type [T] in one composite action that succeeds if at least
     * one of it's parts succeeds.
     *
     * @param block the actions to compose.
     */
    override fun <Type> Type.compose(block: ActionsPack<Type>.() -> Unit)
            where Type : UiBaseActions, Type : UiBaseAssertions,
                  Type : UiInterceptable<UiObjectInteraction, UiObjectAssertion, UiObjectAction> {
        val checks: List<() -> Unit> = ActionsPack(this).apply(block).build()
        invokeViewCompose(checks)
    }

    private fun invokeViewCompose(checks: List<() -> Unit>) {
        val unitedComposedAction = getUnitedComposedAction(checks, kaspresso.libLogger)

        failureLoggingProvider.withLoggingOnFailure {
            flakySafetyProvider.flakySafely(
                unitedComposedAction
            )
        }
    }
}