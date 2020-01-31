package com.kaspersky.components.kautomator.dsl.common.views

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.kaspersky.components.kautomator.dsl.common.KautomatorMarker
import com.kaspersky.components.kautomator.dsl.common.actions.UiBaseActions
import com.kaspersky.components.kautomator.dsl.common.assertions.UiBaseAssertions
import com.kaspersky.components.kautomator.dsl.common.builders.UiViewBuilder
import com.kaspersky.components.kautomator.dsl.common.builders.UiViewSelector
import com.kaspersky.components.kautomator.intercepting.delegate.UiObjectDelegate
import com.kaspersky.components.kautomator.intercepting.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercepting.intercept.UiInterceptable
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAssertion

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
@KautomatorMarker
open class UiBaseView<out T>(selector: UiViewSelector) : UiBaseActions, UiBaseAssertions,
    UiInterceptable<UiObjectInteraction, UiObjectAssertion, UiObjectAction> {

    final override val view: UiObjectDelegate by lazy {
        val delegate = UiObjectDelegate(
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()),
            selector,
            "Object type=${this::class.java.simpleName}"
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
     */
    operator fun invoke(function: T.() -> Unit) {
        view.loadView()
        function(this as T)
    }
}