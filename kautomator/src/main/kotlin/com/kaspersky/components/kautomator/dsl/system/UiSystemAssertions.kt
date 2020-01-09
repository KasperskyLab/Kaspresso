package com.kaspersky.components.kautomator.dsl.system

import com.kaspersky.components.kautomator.dsl.system.UiSystemAssertions.SystemAssertionType.IS_SCREEN_ON
import com.kaspersky.components.kautomator.intercepting.delegate.UiDeviceDelegate
import com.kaspersky.components.kautomator.intercepting.operation.UiOperationType

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

    private fun checkAssertAction(methodName: UiOperationType, action: () -> Boolean) {
        try {
            val result = action.invoke()
            if (!result) throw AssertionError("$methodName method in UiAutomator hasn't checked")
        } catch (exception: Exception) {
            throw AssertionError("$methodName method in UiAutomator hasn't checked", exception)
        }
    }

    enum class SystemAssertionType : UiOperationType {
        IS_SCREEN_ON
    }
}