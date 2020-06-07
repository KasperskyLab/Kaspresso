package com.kaspersky.kaspresso.params

import androidx.test.espresso.PerformException
import androidx.test.uiautomator.UiObjectNotFoundException
import com.kaspersky.components.kautomator.intercept.exception.UnfoundedUiObjectException
import junit.framework.AssertionFailedError

/**
 * The class that holds all the necessary for [com.kaspersky.kaspresso.autoscroll.AutoScrollProviderImpl] and
 * [com.kaspersky.kaspresso.autoscroll.WebAutoScrollProviderImpl] parameters.
 */
class AutoScrollParams private constructor(
    /**
     * The set of exceptions, if caught, the [com.kaspersky.kaspresso.autoscroll.AutoScrollProviderImpl] or
     * [com.kaspersky.kaspresso.autoscroll.WebAutoScrollProviderImpl] will autoscroll.
     */
    val allowedExceptions: Set<Class<out Throwable>>
) {

    companion object {
        fun default() = AutoScrollParams(
            allowedExceptions = setOf(
                PerformException::class.java,
                AssertionFailedError::class.java,
                UiObjectNotFoundException::class.java,
                UnfoundedUiObjectException::class.java
            )
        )
    }
}
