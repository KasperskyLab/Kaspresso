@file:Suppress("unused")
package com.kaspersky.components.kautomator.system

import com.kaspersky.components.kautomator.intercept.delegate.UiDeviceInteractionDelegate
import com.kaspersky.components.kautomator.intercept.operation.UiOperationType
import com.kaspersky.components.kautomator.system.UiSystemAssertions.UiSystemAssertionType.IS_SCREEN_ON

/**
 * Interface with common assertions providing by UiAutomator and executing in the System
 *
 * Provides basic assertions that can be checked everywhere
 *
 * @property view UiDeviceDelegate on which all actions are checked
 */
interface UiSystemAssertions {

    val view: UiDeviceInteractionDelegate

    fun isScreenOn() {
        view.check(IS_SCREEN_ON) {
            checkAssertAction(IS_SCREEN_ON) { isScreenOn }
        }
    }

    private fun checkAssertAction(methodName: UiOperationType, action: () -> Boolean) {
        try {
            val result = action.invoke()
            if (!result) throw AssertionError("$methodName method in UiAutomator hasn't checked")
        } catch (exception: Exception) {
            throw AssertionError("$methodName method in UiAutomator hasn't checked", exception)
        }
    }

    enum class UiSystemAssertionType : UiOperationType {
        IS_SCREEN_ON
    }
}