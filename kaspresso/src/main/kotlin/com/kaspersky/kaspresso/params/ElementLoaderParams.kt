package com.kaspersky.kaspresso.params

import androidx.test.uiautomator.StaleObjectException
import com.kaspersky.components.kautomator.intercept.exception.UnfoundedUiObjectException

class ElementLoaderParams {
    val allowedExceptions: Set<Class<out Throwable>> = setOf(
        AssertionError::class.java,
        UnfoundedUiObjectException::class.java,
        StaleObjectException::class.java,
    )
}
