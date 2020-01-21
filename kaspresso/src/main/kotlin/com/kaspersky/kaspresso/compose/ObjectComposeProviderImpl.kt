package com.kaspersky.kaspresso.compose

import com.kaspersky.components.kautomator.dsl.common.views.UiBaseView
import com.kaspersky.components.kautomator.intercepting.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercepting.intercept.UiInterceptable
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAssertion
import com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack
import com.kaspersky.kaspresso.compose.pack.ActionsPack
import com.kaspersky.kaspresso.failure.FailureLoggingProvider
import com.kaspersky.kaspresso.failure.withLoggingOnFailureIfNotNull
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider
import com.kaspersky.kaspresso.flakysafety.flakySafelyIfNotNull
import com.kaspersky.kaspresso.interceptors.behaviorKautomator.ObjectBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorKautomator.impl.failure.FailureLoggingObjectBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorKautomator.impl.flakysafety.FlakySafeObjectBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.tolibrary.kautomator.compose.ComposeKautomatorObjectInterceptor
import com.kaspersky.kaspresso.interceptors.tolibrary.kautomator.impl.KautomatorObjectInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso

class ObjectComposeProviderImpl(
    private val kaspresso: Kaspresso
) : ObjectComposeProvider {

    override fun composeExternal(block: ActionsOnElementsPack<UiBaseView<*>>.() -> Unit) {
        val (elements, actions) = ActionsOnElementsPack<UiBaseView<*>>().apply(block).build()

        elements.forEach { it.rollComposeInterception() }

        val (flakySafetyProvider, failureLoggingProvider) = getProviders()

        failureLoggingProvider.withLoggingOnFailureIfNotNull {
            flakySafetyProvider.flakySafelyIfNotNull {
                invokeComposed(actions, kaspresso.libLogger)
            }
        }

        elements.forEach { it.rollbackPreviousInterception() }
    }

    override fun <T : UiBaseView<*>> T.composeExternal(block: ActionsPack<T>.() -> Unit) {

        val actions: List<() -> Unit> = ActionsPack(this).apply(block).build()

        rollComposeInterception()

        val (flakySafetyProvider, failureLoggingProvider) = getProviders()

        failureLoggingProvider.withLoggingOnFailureIfNotNull {
            flakySafetyProvider.flakySafelyIfNotNull {
                invokeComposed(actions, kaspresso.libLogger)
            }
        }

        rollbackPreviousInterception()
    }

    private fun getProviders(): Pair<FlakySafetyProvider?, FailureLoggingProvider?> {
        var flakySafetyProvider: FlakySafetyProvider? = null
        var failureLoggingProvider: FailureLoggingProvider? = null

        kaspresso.objectBehaviorInterceptors.forEach { objectBehaviorInterceptor : ObjectBehaviorInterceptor ->
            when (objectBehaviorInterceptor) {
                is FlakySafeObjectBehaviorInterceptor -> flakySafetyProvider = objectBehaviorInterceptor
                is FailureLoggingObjectBehaviorInterceptor -> failureLoggingProvider = objectBehaviorInterceptor
            }
        }

        return Pair(flakySafetyProvider, failureLoggingProvider)
    }

    private fun UiInterceptable<UiObjectInteraction, UiObjectAssertion, UiObjectAction>.rollComposeInterception() {
        val interceptor = ComposeKautomatorObjectInterceptor(kaspresso)

        intercept {
            onCheck(true, interceptor::interceptCheck)
            onPerform(true, interceptor::interceptPerform)
        }
    }

    private fun UiInterceptable<UiObjectInteraction, UiObjectAssertion, UiObjectAction>.rollbackPreviousInterception() {
        val interceptor = KautomatorObjectInterceptor(kaspresso)

        intercept {
            onCheck(true, interceptor::interceptCheck)
            onPerform(true, interceptor::interceptPerform)
        }
    }
}