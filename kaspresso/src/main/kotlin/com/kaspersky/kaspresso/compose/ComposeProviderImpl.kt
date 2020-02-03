package com.kaspersky.kaspresso.compose

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import com.agoda.kakao.common.actions.BaseActions
import com.agoda.kakao.common.assertions.BaseAssertions
import com.agoda.kakao.intercept.Interceptable
import com.kaspersky.components.kautomator.dsl.common.actions.UiBaseActions
import com.kaspersky.components.kautomator.dsl.common.assertions.UiBaseAssertions
import com.kaspersky.components.kautomator.dsl.common.views.UiBaseView
import com.kaspersky.components.kautomator.intercepting.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercepting.intercept.UiInterceptable
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAssertion
import com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack
import com.kaspersky.kaspresso.compose.pack.ActionsPack
import com.kaspersky.kaspresso.failure.FailureLoggingProvider
import com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderImpl
import com.kaspersky.kaspresso.flakysafety.scalpel.FlakySafeInterceptorScalpel.restoreFlakySafeInterceptorToLibs
import com.kaspersky.kaspresso.flakysafety.scalpel.FlakySafeInterceptorScalpel.scalpFlakySafeInterceptorFromLibs
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.params.FlakySafetyParams

/**
 * The implementation of the [ComposeProvider] interface.
 */
class ComposeProviderImpl(
    private val kaspresso: Kaspresso
) : ComposeProvider {

    private val failureLoggingProvider: FailureLoggingProvider = FailureLoggingProviderImpl(kaspresso.libLogger)

    /**
     * Composes a [block] of actions with their views to invoke on in one composite action that succeeds if at least
     * one of it's parts succeeds.
     * Allows to use in one compose block views from Kakao and Kautomator simultaneously
     *
     * @param block the actions to compose.
     */
    override fun compose(timeoutMs: Long?, block: ActionsOnElementsPack.() -> Unit) {
        val composeBranches = ActionsOnElementsPack().apply(block).build()
        val composeElements = composeBranches.map { it.element }
        val composeChecks = composeBranches.map { it.check }

        scalpFlakySafeInterceptorFromLibs(kaspresso)

        val succeedBranchOrderNumber = invokeComposed(
            actions = composeChecks,
            logger = kaspresso.libLogger,
            failureLoggingProvider = failureLoggingProvider,
            flakySafetyProvider = FlakySafetyProviderImpl(
                determineFlakySafetyParams(composeElements, timeoutMs),
                kaspresso.libLogger
            )
        )

        restoreFlakySafeInterceptorToLibs(kaspresso)

        // execution `then` section of a success branch
        composeBranches[succeedBranchOrderNumber].postAction?.invoke()
    }

    private fun determineFlakySafetyParams(elements: List<Any>? = null, timeoutMs: Long? = null): FlakySafetyParams {
        // if there is no information about elements or there are `UiBaseView` (Kautomator) objects among elements
        // then we need to use expanded to maximum FlakySafetyParams (with increased `timeout`, `interval`, expanded `exceptions` and etc)
        val expandToMax: Boolean = elements == null || elements.filterIsInstance<UiBaseView<*>>().isNotEmpty()

        val flakySafetyParams = if (expandToMax) {
            kaspresso.params.flakySafetyParams.merge(
                kaspresso.params.kautomatorFlakySafetyParams
            )
        } else {
            kaspresso.params.flakySafetyParams
        }

        flakySafetyParams.timeoutMs = timeoutMs ?: flakySafetyParams.timeoutMs
        return flakySafetyParams
    }

    /**
     * Composes a [block] of actions on the given view of type [T] in one composite action that succeeds if at least
     * one of it's parts succeeds.
     *
     * @param block the actions to compose.
     */
    override fun <Type> Type.compose(timeoutMs: Long?, block: ActionsPack<Type>.() -> Unit)
            where Type : BaseActions, Type : BaseAssertions,
                  Type : Interceptable<ViewInteraction, ViewAssertion, ViewAction> {
        val actions: List<() -> Unit> = ActionsPack(this).apply(block).build()

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

    /**
     * Composes a [block] of actions on the given view of type [T] in one composite action that succeeds if at least
     * one of it's parts succeeds.
     *
     * @param block the actions to compose.
     */
    override fun <Type> Type.compose(timeoutMs: Long?, block: ActionsPack<Type>.() -> Unit)
            where Type : UiBaseActions, Type : UiBaseAssertions,
                  Type : UiInterceptable<UiObjectInteraction, UiObjectAssertion, UiObjectAction> {
        val actions: List<() -> Unit> = ActionsPack(this).apply(block).build()

        scalpFlakySafeInterceptorFromLibs(kaspresso)

        val flakySafetyParams = kaspresso.params.kautomatorFlakySafetyParams
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