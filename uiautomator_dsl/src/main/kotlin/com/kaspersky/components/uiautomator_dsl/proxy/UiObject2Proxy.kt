package com.kaspersky.components.uiautomator_dsl.proxy

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import com.kaspersky.components.uiautomator_dsl.UiAutomatorConfigurator
import com.kaspersky.components.uiautomator_dsl.intercept.Interceptor

class UiObject2Proxy(private val selector: BySelector) : Proxy<UiObject2?, UiAssert, UiAction> {

    override var interaction: UiObject2? = null
    override var interceptor: Interceptor<UiObject2?, UiAssert, UiAction>? = null

    fun loadView() {
        interaction = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).findObject(selector)
    }

    /**
     * @action must throw exception if something went wrong
     */
    fun check(description: String, action: UiObject2.() -> Unit) {
        val uiAssert: UiAssert = object : UiAssert {
            override fun getDescription(): String = description
            override fun check(interaction: UiObject2) = action.invoke(interaction)
        }
        // todo handle !!
        if (!interceptCheck(uiAssert)) uiAssert.check(interaction!!)
    }

    /**
     * @action must throw exception if something went wrong
     */
    fun perform(description: String, action: UiObject2.() -> Unit) {
        val uiAction: UiAction = object : UiAction {
            override fun getDescription(): String = description
            override fun perform(interaction: UiObject2) = action.invoke(interaction)
        }
        // todo handle !!
        if (!interceptPerform(uiAction)) uiAction.perform(interaction!!)
    }

    override fun screenInterceptors(): Iterable<Interceptor<UiObject2?, UiAssert, UiAction>> =
        // todo
        listOf()

    override fun kakaoInterceptor(): Interceptor<UiObject2?, UiAssert, UiAction>? =
        UiAutomatorConfigurator.uiInterceptor

}