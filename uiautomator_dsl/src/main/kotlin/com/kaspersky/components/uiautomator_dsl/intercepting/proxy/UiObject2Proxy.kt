package com.kaspersky.components.uiautomator_dsl.intercepting.proxy

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import com.kaspersky.components.uiautomator_dsl.UiAutomatorConfigurator
import com.kaspersky.components.uiautomator_dsl.dsl.screen.UiScreen
import com.kaspersky.components.uiautomator_dsl.intercepting.actions.UiAction
import com.kaspersky.components.uiautomator_dsl.intercepting.actions.UiActionImpl
import com.kaspersky.components.uiautomator_dsl.intercepting.actions.UiActionType
import com.kaspersky.components.uiautomator_dsl.intercepting.asserts.UiAssert
import com.kaspersky.components.uiautomator_dsl.intercepting.asserts.UiAssertImpl
import com.kaspersky.components.uiautomator_dsl.intercepting.asserts.UiAssertType
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiObjectInteraction
import com.kaspersky.components.uiautomator_dsl.intercepting.intercept.Interceptor

class UiObject2Proxy(
    selector: BySelector,
    elementClassName: String
) : Proxy<UiObjectInteraction, UiAssert, UiAction> {

    companion object {
        private const val EMPTY_STRING: String = ""
    }

    val objectInteraction: UiObjectInteraction = UiObjectInteraction(
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()),
        selector,
        elementClassName
    )
    override var interceptor: Interceptor<UiObjectInteraction, UiAssert, UiAction>? = null

    fun loadView() {
        objectInteraction.tryToFindUiObject()
    }

    /**
     * @action must throw exception if something went wrong
     */
    fun check(
        type: UiAssertType,
        description: String = EMPTY_STRING,
        assert: UiObject2.() -> Unit
    ) {
        val uiAssert: UiAssert = UiAssertImpl(type, description, assert)
        if (!interceptCheck(uiAssert)) uiAssert.check(objectInteraction)
    }

    fun check(uiAssert: UiAssert) {
        if (!interceptCheck(uiAssert)) uiAssert.check(objectInteraction)
    }

    /**
     * @action must throw exception if something went wrong
     */
    fun perform(
        type: UiActionType,
        description: String = EMPTY_STRING,
        action: UiObject2.() -> Unit
    ) {
        val uiAction: UiAction = UiActionImpl(type, description, action)
        if (!interceptPerform(uiAction)) uiAction.perform(objectInteraction)
    }

    fun perform(uiAction: UiAction) {
        if (!interceptPerform(uiAction)) uiAction.perform(objectInteraction)
    }

    override fun screenInterceptors(): Iterable<Interceptor<UiObjectInteraction, UiAssert, UiAction>> =
        UiScreen.UI_OBJECT_INTERCEPTORS

    override fun kakaoInterceptor(): Interceptor<UiObjectInteraction, UiAssert, UiAction>? =
        UiAutomatorConfigurator.uiObjectInterceptor

}