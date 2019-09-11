package com.kaspersky.kaspresso.autoscroll

import androidx.test.espresso.PerformException
import junit.framework.AssertionFailedError

/**
 * The class that holds all the necessary for [AutoScrollProvider] parameters.
 */
class AutoScrollParams(

    /**
     * The set of exceptions, if caught, the [AutoScrollProvider] will autoscroll.
     */
    var allowedExceptions: MutableSet<Class<out Throwable>> =
        mutableSetOf(
            PerformException::class.java,
            AssertionFailedError::class.java
        )
)