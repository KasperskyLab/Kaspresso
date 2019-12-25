package com.kaspersky.components.uiautomator_dsl.intercepting.proxy

import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import com.kaspersky.components.uiautomator_dsl.UiAutomatorConfigurator
import com.kaspersky.components.uiautomator_dsl.dsl.screen.UiScreen
import com.kaspersky.components.uiautomator_dsl.intercepting.operations.*
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiInteraction
import com.kaspersky.components.uiautomator_dsl.intercepting.intercept.Interceptor

class UiProxy(
    device: UiDevice,
    selector: BySelector,
    elementClassName: String
) : Proxy<UiInteraction, UiAssertion, UiAction> {

    companion object {
        private const val EMPTY_STRING: String = ""
    }

    override val interaction: UiInteraction = UiInteraction(device, selector, elementClassName)
    override var interceptor: Interceptor<UiInteraction, UiAssertion, UiAction>? = null

    fun loadView() {
        interaction.tryToFindUiObject()
    }

    /**
     * @action must throw exception if something went wrong
     */
    fun check(
        type: UiOperationType,
        description: String = EMPTY_STRING,
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
        description: String = EMPTY_STRING,
        action: UiObject2.() -> Unit
    ) {
        val uiAction = getUiOperation(type, description, action)
        perform(uiAction)
    }

    private fun getUiOperation(
        type: UiOperationType,
        description: String = EMPTY_STRING,
        action: UiObject2.() -> Unit
    ) = UiOperationImpl(type, description, action)

    fun check(uiAssertion: UiAssertion) {
        if (!interceptCheck(uiAssertion)) uiAssertion.execute(interaction)
    }

    fun perform(uiOperation: UiAction) {
        if (!interceptPerform(uiOperation)) uiOperation.execute(interaction)
    }

    override fun screenInterceptors(): Iterable<Interceptor<UiInteraction, UiAssertion, UiAction>> =
        UiScreen.UI_INTERCEPTORS

    override fun kakaoInterceptor(): Interceptor<UiInteraction, UiAssertion, UiAction>? =
        UiAutomatorConfigurator.uiInterceptor

}