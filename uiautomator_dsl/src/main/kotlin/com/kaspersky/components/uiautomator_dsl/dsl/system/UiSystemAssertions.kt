package com.kaspersky.components.uiautomator_dsl.dsl.system

import com.kaspersky.components.uiautomator_dsl.dsl.system.UiSystemAssertions.SystemAssertionType.IS_SCREEN_ON
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiActionType
import com.kaspersky.components.uiautomator_dsl.intercepting.delegate.UiDeviceDelegate

/**
 * Interface with common assertions providing by UiAutomator and executing in the System
 *
 * Provides basic assertions that can be checked everywhere
 *
 * @property view UiDeviceDelegate on which all actions are checked
 */
interface UiSystemAssertions {

    val view: UiDeviceDelegate

    fun isScreenOn() {
        view.check(IS_SCREEN_ON) {
            checkAssertAction(IS_SCREEN_ON) { isScreenOn }
        }
    }

    private fun checkAssertAction(methodName: UiActionType, action: () -> Boolean) {
        try {
            val result = action.invoke()
            if (!result) throw AssertionError("$methodName method in UiAutomator hasn't checked")
        } catch (exception: Exception) {
            throw AssertionError("$methodName method in UiAutomator hasn't checked", exception)
        }
    }

    enum class SystemAssertionType : UiActionType {
        IS_SCREEN_ON
    }
}