package com.kaspersky.kaspresso.flakysafety

import com.kaspersky.kaspresso.flakysafety.algorithm.FlakySafetyAlgorithm
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.FlakySafetyParams

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
 * The implementation of the [FlakySafetyProvider] interface.
 * By default, this implementation is using to struggle with flaky UI libs inside a View
 */
class FlakySafetyProviderSimpleImpl(
    private val params: FlakySafetyParams,
    logger: UiTestLogger
) : FlakySafetyProvider {

    private val flakySafetyAlgorithm = FlakySafetyAlgorithm(logger)

    /**
     * Invokes the given [action] flaky safely.
     *
     * @param action the action to invoke.
     * @return the [action] invocation result.
     * @throws Throwable if all attempts failed.
     */
    @Throws(Throwable::class)
    override fun <T> flakySafely(action: () -> T): T =
        flakySafetyAlgorithm.invokeFlakySafely(
            params = params,
            action = action
        )

    /**
     * Invokes the given [action] flaky safely.
     *
     * @param timeoutMs the timeout during which attempts will be made.
     * @param intervalMs the interval at which attempts will be made.
     * @param allowedExceptions the set of exceptions, if caught, attempt will continue.
     * @param failureMessage the message to log on failure.
     * @param action the action to invoke.
     *
     * @return the [action] invocation result.
     *
     * @throws Throwable if all attempts failed.
     */
    @Throws(Throwable::class)
    override fun <T> flakySafely(
        timeoutMs: Long?,
        intervalMs: Long?,
        allowedExceptions: Set<Class<out Throwable>>?,
        failureMessage: String?,
        action: () -> T
    ): T = flakySafetyAlgorithm.invokeFlakySafely(
        params = FlakySafetyParams(
            timeoutMs ?: params.timeoutMs,
            intervalMs ?: params.intervalMs,
            allowedExceptions ?: params.allowedExceptions
        ),
        failureMessage = failureMessage,
        action = action
    )
}
