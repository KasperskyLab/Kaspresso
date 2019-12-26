package com.kaspersky.components.uiautomator_dsl.intercepting.delegate

import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import com.kaspersky.components.uiautomator_dsl.UiAutomatorConfigurator
import com.kaspersky.components.uiautomator_dsl.dsl.screen.UiScreen
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.*
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiObjectInteraction
import com.kaspersky.components.uiautomator_dsl.intercepting.intercept.UiInterceptor

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
    ) = UiObjectOperation(type, description, action)

    fun check(uiAssertion: UiObjectAssertion) {
        if (!interceptCheck(uiAssertion)) uiAssertion.execute(interaction)
    }

    fun perform(uiObjectOperation: UiObjectAction) {
        if (!interceptPerform(uiObjectOperation)) uiObjectOperation.execute(interaction)
    }

    override fun screenInterceptors(): Iterable<UiInterceptor<UiObjectInteraction, UiObjectAssertion, UiObjectAction>> =
        UiScreen.UI_OBJECT_INTERCEPTORS

    override fun kakaoInterceptor(): UiInterceptor<UiObjectInteraction, UiObjectAssertion, UiObjectAction>? =
        UiAutomatorConfigurator.uiObjectInterceptor

}