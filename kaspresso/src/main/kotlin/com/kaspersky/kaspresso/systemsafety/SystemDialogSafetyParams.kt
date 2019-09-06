package com.kaspersky.kaspresso.systemsafety

import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.PerformException

class SystemDialogSafetyParams(
    var allowedExceptions: MutableSet<Class<out Throwable>> =
        mutableSetOf(
            PerformException::class.java,
            NoMatchingViewException::class.java,
            AssertionError::class.java
        )
)