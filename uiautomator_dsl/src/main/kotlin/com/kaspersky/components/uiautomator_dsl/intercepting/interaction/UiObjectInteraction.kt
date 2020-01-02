package com.kaspersky.components.uiautomator_dsl.intercepting.interaction

import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiObjectAction
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiObjectAssertion
import com.kaspersky.components.uiautomator_dsl.intercepting.exception.UnfoundedUiObjectException

/**
 * Provides an interaction to work with the UiView described by [selector]
 */
class UiObjectInteraction(
    val device: UiDevice,
    val selector: BySelector,
    // UiButton, UiTextView, UiEditText, etc.
    val elementClassName: String
) : UiInteraction<UiObjectAssertion, UiObjectAction> {

    var uiObject2: UiObject2? = null
        private set

    fun tryToFindUiObject() {
        uiObject2 = device.findObject(selector)
    }

    override fun check(assertion: UiObjectAssertion) {
        assertion.execute(uiObject2 ?: throw UnfoundedUiObjectException(selector))
    }

    override fun perform(action: UiObjectAction) {
        action.execute(uiObject2 ?: throw UnfoundedUiObjectException(selector))
    }
}