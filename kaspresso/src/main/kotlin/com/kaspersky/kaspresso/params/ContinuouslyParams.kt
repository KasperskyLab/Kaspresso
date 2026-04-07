package com.kaspersky.kaspresso.params

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
 * The class that holds all the necessary for [com.kaspersky.kaspresso.flakysafety.ContinuouslyProviderImpl] parameters.
 */
class ContinuouslyParams(
    timeoutMs: Long,
    intervalMs: Long
) {
    companion object {
        const val defaultTimeoutMs: Long = 10_000L
        const val defaultIntervalMs: Long = 500L

        fun default() = ContinuouslyParams(
            timeoutMs = defaultTimeoutMs,
            intervalMs = defaultIntervalMs
        )

        fun custom(
            timeoutMs: Long = defaultTimeoutMs,
            intervalMs: Long = defaultIntervalMs
        ) = ContinuouslyParams(
            timeoutMs = timeoutMs,
            intervalMs = intervalMs
        )
    }

    init {
        require(timeoutMs > 0) { "Timeout must be > 0" }
        require(intervalMs > 0) { "Interval must be > 0" }
        require(timeoutMs > intervalMs) { "Timeout must be > interval" }
    }

    /**
     * The timeout during which attempts will be made by the
     * [com.kaspersky.kaspresso.flakysafety.ContinuouslyProviderImpl].
     */
    var timeoutMs: Long = timeoutMs
        @Deprecated("Do not mutate this property, just use public constructor to create new instance")
        set(value) {
            require(timeoutMs > 0) { "Timeout must be > 0" }
            require(timeoutMs > intervalMs) { "Timeout must be > interval" }
            field = value
        }

    /**
     * The interval at which attempts will be made by the [com.kaspersky.kaspresso.flakysafety.ContinuouslyProviderImpl].
     */
    var intervalMs: Long = intervalMs
        @Deprecated("Do not mutate this property, just use public constructor to create new instance")
        set(value) {
            require(intervalMs > 0) { "Interval must be > 0" }
            require(timeoutMs > intervalMs) { "Timeout must be > interval" }
            field = value
        }
}
