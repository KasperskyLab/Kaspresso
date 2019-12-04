package com.kaspersky.components.uiautomator_dsl.common.views

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import com.kaspersky.components.uiautomator_dsl.common.UiAutomatorDslMarker
import com.kaspersky.components.uiautomator_dsl.common.actions.UiBaseActions
import com.kaspersky.components.uiautomator_dsl.common.assertions.UiBaseAssertions
import com.kaspersky.components.uiautomator_dsl.common.builders.UiViewBuilder

@Suppress("UNCHECKED_CAST")
@UiAutomatorDslMarker
open class UiBaseView<out T> : UiBaseActions, UiBaseAssertions {

    private val innerView: UiObject2?
    override val actionsView: UiObject2? get() = innerView
    override val assertionsView: UiObject2? get() = innerView

    constructor(selector: BySelector) {
        innerView = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).findObject(selector)
    }

    constructor(func: UiViewBuilder.() -> Unit) {
        innerView = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
            .findObject(UiViewBuilder().apply(func).build())
    }

    /**
     * @param safely - Set to true only when you are not sure if the [innerView] is null or not.
     * If true, the function invocation will be skipped if the view does not exist, so Exception
     * won't be thrown.
     */
    operator fun invoke(safely: Boolean = false, function: T.() -> Unit) {
        if (safely && innerView == null) return
        function(this as T)
    }

    infix fun perform(function: T.() -> Unit): T {
        function(this as T)
        return this
    }
}