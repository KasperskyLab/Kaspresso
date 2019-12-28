package com.kaspersky.components.uiautomator_dsl.intercepting.exception

import androidx.test.uiautomator.BySelector

/**
 * The exception is thrown in case if UiObject2 is not found on the screen
 */
class UnfoundedUiObjectException(selector: BySelector)
    : RuntimeException("The UiObject2 was not found on the screen. The selector=$selector")