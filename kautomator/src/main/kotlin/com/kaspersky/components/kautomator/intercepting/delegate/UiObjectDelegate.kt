package com.kaspersky.components.kautomator.intercepting.delegate

import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import com.kaspersky.components.kautomator.KautomatorConfigurator
import com.kaspersky.components.kautomator.dsl.common.builders.UiViewSelector
import com.kaspersky.components.kautomator.dsl.screen.UiScreen
import com.kaspersky.components.kautomator.intercepting.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercepting.intercept.UiInterceptor
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAssertion
import com.kaspersky.components.kautomator.intercepting.operation.UiOperationBaseImpl
import com.kaspersky.components.kautomator.intercepting.operation.UiOperationType

/**
 * Delegation class for [androidx.test.uiautomator.UiObject2].
 * Wraps all available public calls and intercepts into [check] and [perform].
 *
 * @see UiDelegate
 * @see UiInterceptor
 */
class UiObjectDelegate(
    val device: UiDevice,
    val selector: UiViewSelector,
    description: String
) : UiDelegate<UiObjectInteraction, UiObjectAssertion, UiObjectAction> {

    override val interaction: UiObjectInteraction = UiObjectInteraction(device, selector, description)
    override var interceptor: UiInterceptor<UiObjectInteraction, UiObjectAssertion, UiObjectAction>? = null

    fun loadView(): Boolean = interaction.tryToFindUiObject()

    fun check(
        type: UiOperationType,
        description: String? = null,
        assert: UiObject2.() -> Unit
    ) {
        val uiAssertion = getUiActionBase(type, description, assert)
        check(uiAssertion)
    }

    fun perform(
        type: UiOperationType,
        description: String? = null,
        action: UiObject2.() -> Unit
    ) {
        val uiAction = getUiActionBase(type, description, action)
        perform(uiAction)
    }

    private fun getUiActionBase(
        type: UiOperationType,
        description: String? = null,
        action: UiObject2.() -> Unit
    ) = UiOperationBaseImpl(type, description, action)

    fun check(uiAssertion: UiObjectAssertion) {
        if (!interceptCheck(uiAssertion)) interaction.check(uiAssertion)
    }

    fun perform(uiAction: UiObjectAction) {
        if (!interceptPerform(uiAction)) interaction.perform(uiAction)
    }

    override fun screenInterceptors(): Iterable<UiInterceptor<UiObjectInteraction, UiObjectAssertion, UiObjectAction>> =
        UiScreen.UI_OBJECT_INTERCEPTORS

    override fun globalInterceptor(): UiInterceptor<UiObjectInteraction, UiObjectAssertion, UiObjectAction>? =
        KautomatorConfigurator.uiObjectInterceptor
}