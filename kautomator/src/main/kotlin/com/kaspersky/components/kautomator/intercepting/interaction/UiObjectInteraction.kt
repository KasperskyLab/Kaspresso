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
    val description: String
) : UiInteraction<UiObjectAssertion, UiObjectAction> {

    var uiObject2: UiObject2? = null
        private set

    /**
     * Tries to find UiObject2 with given selector
     * @return true if object was found, false otherwise
     */
    fun tryToFindUiObject(): Boolean {
        val uiObjects = device.findObjects(selector.bySelector)
        if (uiObjects.isNotEmpty() && selector.index < uiObjects.size) {
            uiObject2 = uiObjects[selector.index]
            return true
        }
        return false
    }

    override fun check(assertion: UiObjectAssertion) {
        assertion.execute(uiObject2 ?: throw UnfoundedUiObjectException(selector))
    }

    override fun perform(action: UiObjectAction) {
        action.execute(uiObject2 ?: throw UnfoundedUiObjectException(selector))
    }

    override fun toString(): String {
        return "UiObjectInteraction(device=$device, selector=$selector, description='$description', uiObject2=$uiObject2)"
    }
}