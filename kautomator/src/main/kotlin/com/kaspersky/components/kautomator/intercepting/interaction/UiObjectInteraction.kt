package com.kaspersky.components.kautomator.intercepting.interaction

import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import com.kaspersky.components.kautomator.dsl.common.builders.UiViewSelector
import com.kaspersky.components.kautomator.intercepting.exception.UnfoundedUiObjectException
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAssertion

/**
 * Provides an interaction to work with the UiView described by [selector]
 */
class UiObjectInteraction(
    val device: UiDevice,
    val selector: UiViewSelector,
    // UiButton, UiTextView, UiEditText, etc.
    val elementClassName: String
) : UiInteraction<UiObjectAssertion, UiObjectAction> {

    var uiObject2: UiObject2? = null
        private set

    fun tryToFindUiObject() {
        val uiObjects = device.findObjects(selector.bySelector)
        if (uiObjects.isNotEmpty() && selector.index < uiObjects.size)
            uiObject2 = uiObjects[selector.index]
    }

    override fun check(assertion: UiObjectAssertion) {
        assertion.execute(uiObject2 ?: throw UnfoundedUiObjectException(selector))
    }

    override fun perform(action: UiObjectAction) {
        action.execute(uiObject2 ?: throw UnfoundedUiObjectException(selector))
    }
}