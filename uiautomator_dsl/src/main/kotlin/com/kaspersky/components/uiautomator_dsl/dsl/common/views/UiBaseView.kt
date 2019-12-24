package com.kaspersky.components.uiautomator_dsl.dsl.common.views

import androidx.test.uiautomator.BySelector
import com.kaspersky.components.uiautomator_dsl.dsl.common.UiAutomatorDslMarker
import com.kaspersky.components.uiautomator_dsl.dsl.common.actions.UiBaseActions
import com.kaspersky.components.uiautomator_dsl.dsl.common.assertions.UiBaseAssertions
import com.kaspersky.components.uiautomator_dsl.dsl.common.builders.UiViewBuilder
import com.kaspersky.components.uiautomator_dsl.intercepting.actions.UiAction
import com.kaspersky.components.uiautomator_dsl.intercepting.asserts.UiAssert
import com.kaspersky.components.uiautomator_dsl.intercepting.intercept.Interceptable
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiInteraction
import com.kaspersky.components.uiautomator_dsl.intercepting.proxy.UiProxy

@Suppress("UNCHECKED_CAST")
@UiAutomatorDslMarker
open class UiBaseView<out T>(selector: BySelector) : UiBaseActions, UiBaseAssertions,
    Interceptable<UiInteraction, UiAssert, UiAction> {

    override val view: UiProxy = UiProxy(selector, this::class.java.simpleName)
    override val actionsView: UiProxy get() = view
    override val assertionsView: UiProxy get() = view

    constructor(func: UiViewBuilder.() -> Unit) : this(UiViewBuilder().apply(func).build())

    /**
     * @param safely - Set to true only when you are not sure if the [innerView] is null or not.
     * If true, the function invocation will be skipped if the view does not exist, so Exception
     * won't be thrown.
     */
    operator fun invoke(safely: Boolean = false, function: T.() -> Unit) {
        view.loadView()
        // todo remove it?
        if (safely && view.interaction.uiObject2 == null) return
        function(this as T)
    }
}