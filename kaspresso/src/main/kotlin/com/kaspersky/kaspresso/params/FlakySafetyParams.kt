package com.kaspersky.kaspresso.params

import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.PerformException
import androidx.test.uiautomator.StaleObjectException
import com.kaspersky.components.kautomator.intercept.exception.UnfoundedUiObjectException
import com.kaspersky.kaspresso.internal.exceptions.RootViewWithoutFocusWrapperException
import junit.framework.AssertionFailedError

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

/**
 * The class that holds all the necessary for [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl] parameters.
 */
class FlakySafetyParams(
    timeoutMs: Long,
    intervalMs: Long,

    /**
     * The set of exceptions, if caught, the [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl] will continue
     * to attempt.
     */
    val allowedExceptions: Set<Class<out Throwable>>
) {
    companion object {
        const val defaultTimeoutMs: Long = 10_000L
        const val defaultIntervalMs: Long = 500L
        val defaultAllowedExceptions: Set<Class<out Throwable>> = setOf(
            PerformException::class.java,
            NoMatchingViewException::class.java,
            AssertionError::class.java,
            AssertionFailedError::class.java,
            UnfoundedUiObjectException::class.java,
            StaleObjectException::class.java,
            IllegalStateException::class.java,
            RootViewWithoutFocusWrapperException::class.java,
        )

        fun default() = FlakySafetyParams(
            timeoutMs = defaultTimeoutMs,
            intervalMs = defaultIntervalMs,
            allowedExceptions = defaultAllowedExceptions
        )

        fun custom(
            timeoutMs: Long = defaultTimeoutMs,
            intervalMs: Long = defaultIntervalMs,
            allowedExceptions: Set<Class<out Throwable>> = defaultAllowedExceptions
        ): FlakySafetyParams = FlakySafetyParams(
            timeoutMs = timeoutMs,
            intervalMs = intervalMs,
            allowedExceptions = allowedExceptions
        )
    }

    init {
        require(timeoutMs > 0) { "Timeout must be > 0" }
        require(intervalMs > 0) { "Interval must be > 0" }
        require(timeoutMs > intervalMs) { "Timeout must be > interval" }
    }

    /**
     * The timeout during which attempts will be made by the
     * [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl].
     */
    var timeoutMs: Long = timeoutMs
        @Deprecated("Do not mutate this property, just use public constructor to create new instance")
        set(value) {
            require(timeoutMs > 0) { "Timeout must be > 0" }
            require(timeoutMs > intervalMs) { "Timeout must be > interval" }
            field = value
        }

    /**
     * The interval at which attempts will be made by the
     * [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl].
     */
    var intervalMs: Long = intervalMs
        @Deprecated("Do not mutate this property, just use public constructor to create new instance")
        set(value) {
            require(intervalMs > 0) { "Interval must be > 0" }
            require(timeoutMs > intervalMs) { "Timeout must be > interval" }
            field = value
        }
}
