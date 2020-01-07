package com.kaspersky.components.uiautomator_dsl.dsl.common.views

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.kaspersky.components.uiautomator_dsl.dsl.common.UiAutomatorDslMarker
import com.kaspersky.components.uiautomator_dsl.dsl.common.actions.UiBaseActions
import com.kaspersky.components.uiautomator_dsl.dsl.common.assertions.UiBaseAssertions
import com.kaspersky.components.uiautomator_dsl.dsl.common.builders.UiViewBuilder
import com.kaspersky.components.uiautomator_dsl.dsl.common.builders.UiViewSelector
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiObjectAction
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiObjectAssertion
import com.kaspersky.components.uiautomator_dsl.intercepting.delegate.UiObjectDelegate
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiObjectInteraction
import com.kaspersky.components.uiautomator_dsl.intercepting.intercept.UiInterceptable

/**
 * Base class for all UiAutomator DSL views
 *
 * This base class allows create new custom view with ease. All you
 * have to do is to extend this class, implement all necessarily additional
 * actions/assertions interfaces and override necessary constructors
 *
 * @param T Type of your custom view. Needs to be defined to enable invoke() for descendants
 */
@Suppress("UNCHECKED_CAST")
@UiAutomatorDslMarker
open class UiBaseView<out T>(selector: UiViewSelector) : UiBaseActions, UiBaseAssertions,
    UiInterceptable<UiObjectInteraction, UiObjectAssertion, UiObjectAction> {

    final override val view: UiObjectDelegate by lazy {
        val delegate = UiObjectDelegate(
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()),
            selector,
            this::class.java.simpleName
        )
        delegate.loadView()
        delegate
    }

    /**
     * Constructs view class with UiObject interaction from given UiViewBuilder
     *
     * @param function UiViewBuilder which will result in [UiObjectInteraction]
     *
     * @see UiViewBuilder
     */
    constructor(function: UiViewBuilder.() -> Unit) : this(UiViewBuilder().apply(function).build())

    /**
     * Operator that allows usage of DSL style
     *
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