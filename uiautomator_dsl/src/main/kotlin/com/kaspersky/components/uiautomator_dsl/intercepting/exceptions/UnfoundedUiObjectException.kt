package com.kaspersky.components.uiautomator_dsl.intercepting.exceptions

import androidx.test.uiautomator.BySelector

class UnfoundedUiObjectException(selector: BySelector)
    : RuntimeException("The UiObject2 was not found on the screen. The selector=$selector")