package com.kaspersky.components.uiautomatordsl.intercepting.exception

import com.kaspersky.components.uiautomatordsl.dsl.common.builders.UiViewSelector

/**
 * The exception is thrown in case if UiObject2 is not found on the screen
 */
class UnfoundedUiObjectException(selector: UiViewSelector)
    : RuntimeException("The UiObject2 was not found on the screen. " +
        "The selector=${selector.bySelector}, index=${selector.index}")