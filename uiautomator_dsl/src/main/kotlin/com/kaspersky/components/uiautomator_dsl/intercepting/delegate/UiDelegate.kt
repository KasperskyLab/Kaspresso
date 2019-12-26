package com.kaspersky.components.uiautomator_dsl.intercepting.delegate

import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import com.kaspersky.components.uiautomator_dsl.UiAutomatorConfigurator
import com.kaspersky.components.uiautomator_dsl.dsl.screen.UiScreen
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.*
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiInteraction
import com.kaspersky.components.uiautomator_dsl.intercepting.intercept.UiInterceptor

class UiDelegate(
    device: UiDevice,
    selector: BySelector,
    elementClassName: String
) : Delegate<UiInteraction, UiAssertion, UiAction> {

    override val interaction: UiInteraction = UiInteraction(device, selector, elementClassName)
    override var interceptor: UiInterceptor<UiInteraction, UiAssertion, UiAction>? = null

    fun loadView() {
        interaction.tryToFindUiObject()
    }

    /**
     * @action must throw exception if something went wrong
     */
    fun check(
        type: UiOperationType,
        description: String? = null,
        assert: UiObject2.() -> Unit
    ) {
        val uiAssertion = getUiOperation(type, description, assert)
        check(uiAssertion)
    }

    /**
     * @action must throw exception if something went wrong
     */
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
    ) = UiOperationImpl(type, description, action)

    fun check(uiAssertion: UiAssertion) {
        if (!interceptCheck(uiAssertion)) uiAssertion.execute(interaction)
    }

    fun perform(uiOperation: UiAction) {
        if (!interceptPerform(uiOperation)) uiOperation.execute(interaction)
    }

    override fun screenInterceptors(): Iterable<UiInterceptor<UiInteraction, UiAssertion, UiAction>> =
        UiScreen.UI_INTERCEPTORS

    override fun kakaoInterceptor(): UiInterceptor<UiInteraction, UiAssertion, UiAction>? =
        UiAutomatorConfigurator.uiInterceptor

}