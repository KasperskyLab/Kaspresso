package com.kaspersky.kaspresso.flakysafety.algorithm

import com.kaspersky.kaspresso.internal.extensions.other.isAllowed
import com.kaspersky.kaspresso.internal.extensions.other.withMessage
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.FlakySafetyParams
import java.util.Timer
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.schedule
import kotlin.concurrent.withLock

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

class FlakySafetyAlgorithm(
    private val logger: UiTestLogger
) {

    private val lock = ReentrantLock()
    private val condition = lock.newCondition()

    /**
     * Attempts to invoke the given [action].
     */
    fun <T> invokeFlakySafely(
        params: FlakySafetyParams,
        failureMessage: String? = null,
        action: () -> T
    ): T {
        var cachedError: Throwable
        val startTime = System.currentTimeMillis()

        do {
            try {
                return action.invoke()
            } catch (error: Throwable) {
                if (error.isAllowed(params.allowedExceptions)) {
                    cachedError = error
                    lock.withLock {
                        Timer().schedule(params.intervalMs) {
                            lock.withLock { condition.signalAll() }
                        }
                        condition.await()
                    }
                } else {
                    throw error
                }
            }
        } while (System.currentTimeMillis() - startTime <= params.timeoutMs)

        logger.e(
            "All attempts to interact for ${params.timeoutMs} ms totally failed " +
                    "because of ${cachedError.javaClass.simpleName}"
        )

        throw cachedError.withMessage(failureMessage)
    }
}
