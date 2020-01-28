package com.kaspersky.kaspresso.params

import androidx.test.espresso.PerformException
import junit.framework.AssertionFailedError

/**
 * The class that holds all the necessary for [com.kaspersky.kaspresso.autoscroll.AutoScrollProviderImpl] and
 * [com.kaspersky.kaspresso.autoscroll.WebAutoScrollProviderImpl] parameters.
 */
data class AutoScrollParams(

    /**
     * The set of exceptions, if caught, the [com.kaspersky.kaspresso.autoscroll.AutoScrollProviderImpl] or
     * [com.kaspersky.kaspresso.autoscroll.WebAutoScrollProviderImpl] will autoscroll.
     */
    var allowedExceptions: MutableSet<Class<out Throwable>> =
        mutableSetOf(
            PerformException::class.java,
            AssertionFailedError::class.java
        )
)