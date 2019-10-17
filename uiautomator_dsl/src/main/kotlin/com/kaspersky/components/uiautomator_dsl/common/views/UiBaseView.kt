package com.kaspersky.components.uiautomator_dsl.common.views

import android.app.Instrumentation
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import com.kaspersky.components.uiautomator_dsl.common.UiAutomatorDslMarker
import com.kaspersky.components.uiautomator_dsl.common.actions.UiBaseActions
import com.kaspersky.components.uiautomator_dsl.common.assertions.UiBaseAssertions

@Suppress("UNCHECKED_CAST")
@UiAutomatorDslMarker
open class UiBaseView<out T> : UiBaseActions, UiBaseAssertions {

    private val innerView: UiObject2?
    override val actionsView: UiObject2? get() = innerView
    override val assertionsView: UiObject2? get() = innerView

    constructor(instrumentation: Instrumentation, selector: BySelector) {
        innerView = UiDevice.getInstance(instrumentation).findObject(selector)
    }

    operator fun invoke(function: T.() -> Unit) {
        function(this as T)
    }

    infix fun perform(function: T.() -> Unit): T {
        function(this as T)
        return this
    }
}