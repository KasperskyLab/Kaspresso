package com.kaspersky.components.kautomator.component.common.views

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.kaspersky.components.kautomator.common.Environment
import com.kaspersky.components.kautomator.common.KautomatorInUnitTestException
import com.kaspersky.components.kautomator.common.environment
import com.kaspersky.components.kautomator.component.common.KautomatorMarker
import com.kaspersky.components.kautomator.component.common.actions.UiBaseActions
import com.kaspersky.components.kautomator.component.common.assertions.UiBaseAssertions
import com.kaspersky.components.kautomator.component.common.builders.UiViewBuilder
import com.kaspersky.components.kautomator.component.common.builders.UiViewSelector
import com.kaspersky.components.kautomator.intercept.base.UiInterceptable
import com.kaspersky.components.kautomator.intercept.delegate.UiObjectInteractionDelegate
import com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAssertion

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

    final override val view: UiObjectInteractionDelegate by lazy(LazyThreadSafetyMode.NONE) {
        if (environment != Environment.AndroidRuntime) throw KautomatorInUnitTestException()

        val delegate = UiObjectInteractionDelegate(
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()),
            selector,
            "Object type=${this::class.java.simpleName}"
        )
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
        function(this as T)
    }
}
