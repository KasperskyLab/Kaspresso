package com.kaspersky.components.kautomator.intercept.interaction

import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import com.kaspersky.components.kautomator.component.common.builders.UiViewSelector
import com.kaspersky.components.kautomator.intercept.exception.UnfoundedUiObjectException
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAssertion

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
