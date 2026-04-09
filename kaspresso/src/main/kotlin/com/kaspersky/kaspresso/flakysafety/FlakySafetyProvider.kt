package com.kaspersky.kaspresso.flakysafety

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
 * The interface to provide the flaky safety functionality.
 */
interface FlakySafetyProvider {

    /**
     * Invokes the given [action] flaky safely.
     *
     * @param action the action to invoke.
     *
     * @return the [action] invocation result.
     */
    fun <T> flakySafely(action: () -> T): T

    /**
     * Invokes the given [action] flaky safely.
     *
     * @param timeoutMs the timeout during which attempts will be made.
     * @param intervalMs the interval at which attempts will be made.
     * @param allowedExceptions the set of exceptions that allow to continue an attempt of execution.
     * @param failureMessage the message to log on failure.
     * @param action the action to invoke.
     *
     * @return the [action] invocation result.
     */
    fun <T> flakySafely(
        timeoutMs: Long? = null,
        intervalMs: Long? = null,
        allowedExceptions: Set<Class<out Throwable>>? = null,
        failureMessage: String? = null,
        action: () -> T
    ): T
}
