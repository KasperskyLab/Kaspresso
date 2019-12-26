package com.kaspersky.components.uiautomator_dsl.dsl.common.views

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiDevice
import com.kaspersky.components.uiautomator_dsl.dsl.common.UiAutomatorDslMarker
import com.kaspersky.components.uiautomator_dsl.dsl.common.actions.UiBaseActions
import com.kaspersky.components.uiautomator_dsl.dsl.common.assertions.UiBaseAssertions
import com.kaspersky.components.uiautomator_dsl.dsl.common.builders.UiViewBuilder
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiObjectAction
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiObjectAssertion
import com.kaspersky.components.uiautomator_dsl.intercepting.intercept.UiInterceptable
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiObjectInteraction
import com.kaspersky.components.uiautomator_dsl.intercepting.delegate.UiObjectDelegate

@Suppress("UNCHECKED_CAST")
@UiAutomatorDslMarker
open class UiBaseView<out T>(selector: BySelector) : UiBaseActions, UiBaseAssertions,
    UiInterceptable<UiObjectInteraction, UiObjectAssertion, UiObjectAction> {

    override val view: UiObjectDelegate = UiObjectDelegate(
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()),
        selector,
        this::class.java.simpleName
    )
    override val actionsView: UiObjectDelegate get() = view
    override val assertionsView: UiObjectDelegate get() = view

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