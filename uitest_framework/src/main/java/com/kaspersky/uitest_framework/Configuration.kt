package com.kaspersky.uitest_framework

import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.PerformException
import java.util.concurrent.TimeUnit

object Configuration {

    const val MAX_LAUNCH_TIME_MS = 10000L

//    /**
//     * global registry for [ActionInterceptor]'s
//     */
//    val actionInterceptors = mutableListOf<ActionInterceptor>()
//
//    /**
//     * global registry for [AssertionInterceptor]'s
//     */
//    val assertionInterceptors = mutableListOf<AssertionInterceptor>()

    /**
     * Changing this value will affect all subsequent actions/checks wait frequency
     */
    var waiterFrequencyMs: Long = 50L

    /**
     * Changing this value will affect all subsequent actions/checks wait timeout
     */
    var waiterTimeoutMs: Long = TimeUnit.SECONDS.toMillis(2)

    /**
     * Exceptions to be waited for; any unregistered exceptions will be propagated
     */
    var waiterAllowedExceptions = setOf(
            PerformException::class.java,
            NoMatchingViewException::class.java,
            AssertionError::class.java,
            PerformException::class.java
    )
}