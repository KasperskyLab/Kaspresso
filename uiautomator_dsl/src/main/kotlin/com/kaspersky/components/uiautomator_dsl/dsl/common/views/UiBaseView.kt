package com.kaspersky.components.uiautomator_dsl.dsl.common.views

import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiObject2
import com.kaspersky.components.uiautomator_dsl.dsl.common.UiAutomatorDslMarker
import com.kaspersky.components.uiautomator_dsl.dsl.common.actions.UiBaseActions
import com.kaspersky.components.uiautomator_dsl.dsl.common.assertions.UiBaseAssertions
import com.kaspersky.components.uiautomator_dsl.dsl.common.builders.UiViewBuilder
import com.kaspersky.components.uiautomator_dsl.intercepting.intercept.Interceptable
import com.kaspersky.components.uiautomator_dsl.intercepting.proxy.UiAction
import com.kaspersky.components.uiautomator_dsl.intercepting.proxy.UiAssert
import com.kaspersky.components.uiautomator_dsl.intercepting.proxy.UiObject2Proxy

@Suppress("UNCHECKED_CAST")
@UiAutomatorDslMarker
open class UiBaseView<out T>(private val selector: BySelector) : UiBaseActions, UiBaseAssertions,
    Interceptable<UiObject2?, UiAssert, UiAction> {

    override val view: UiObject2Proxy = UiObject2Proxy(selector)
    override val actionsView: UiObject2Proxy get() = view
    override val assertionsView: UiObject2Proxy get() = view

    constructor(func: UiViewBuilder.() -> Unit) : this(UiViewBuilder().apply(func).build())

    /**
     * @param safely - Set to true only when you are not sure if the [innerView] is null or not.
     * If true, the function invocation will be skipped if the view does not exist, so Exception
     * won't be thrown.
     */
    operator fun invoke(safely: Boolean = false, function: T.() -> Unit) {
        view.loadView()
        if (safely && view.interaction == null) return
        function(this as T)
    }
}