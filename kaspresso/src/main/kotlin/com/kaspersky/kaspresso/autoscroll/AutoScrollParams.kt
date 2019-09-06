package com.kaspersky.kaspresso.autoscroll

import androidx.test.espresso.PerformException
import junit.framework.AssertionFailedError

class AutoScrollParams(
    var allowedExceptions: MutableSet<Class<out Throwable>> =
        mutableSetOf(
            PerformException::class.java,
            AssertionFailedError::class.java
        )
)