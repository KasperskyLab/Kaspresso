package com.kaspersky.components.uiautomator_dsl.intercepting.delegate

import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import com.kaspersky.components.uiautomator_dsl.UiAutomatorDslConfigurator
import com.kaspersky.components.uiautomator_dsl.dsl.screen.UiScreen
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.*
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiObjectInteraction
import com.kaspersky.components.uiautomator_dsl.intercepting.intercept.UiInterceptor

/**
 * Delegation class for [androidx.test.uiautomator.UiObject2].
 * Wraps all available public calls and intercepts into [check] and [perform].
 *
 * @see UiDelegate
 * @see UiInterceptor
 */
class UiObjectDelegate(
    device: UiDevice,
    selector: BySelector,
    elementClassName: String
) : UiDelegate<UiObjectInteraction, UiObjectAssertion, UiObjectAction> {

    override val interaction: UiObjectInteraction = UiObjectInteraction(device, selector, elementClassName)
    override var interceptor: UiInterceptor<UiObjectInteraction, UiObjectAssertion, UiObjectAction>? = null

    fun loadView() {
        interaction.tryToFindUiObject()
    }

    fun check(
        type: UiOperationType,
        description: String? = null,
        assert: UiObject2.() -> Unit
    ) {
        val uiAssertion = getUiOperation(type, description, assert)
        check(uiAssertion)
    }

    fun perform(
        type: UiOperationType,
        description: String? = null,
        action: UiObject2.() -> Unit
    ) {
        val uiAction = getUiOperation(type, description, action)
        perform(uiAction)
    }

    private fun getUiOperation(
        type: UiOperationType,
        description: String? = null,
        action: UiObject2.() -> Unit
    ) = UiObjectOperation(type, description, action)

    fun check(uiAssertion: UiObjectAssertion) {
        if (!interceptCheck(uiAssertion)) uiAssertion.execute(interaction)
    }

    fun perform(uiObjectOperation: UiObjectAction) {
        if (!interceptPerform(uiObjectOperation)) uiObjectOperation.execute(interaction)
    }

    override fun screenInterceptors(): Iterable<UiInterceptor<UiObjectInteraction, UiObjectAssertion, UiObjectAction>> =
        UiScreen.UI_OBJECT_INTERCEPTORS

    override fun globalInterceptor(): UiInterceptor<UiObjectInteraction, UiObjectAssertion, UiObjectAction>? =
        UiAutomatorDslConfigurator.uiObjectInterceptor

}