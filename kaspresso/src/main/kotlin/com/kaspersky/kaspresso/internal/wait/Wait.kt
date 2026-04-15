package com.kaspersky.kaspresso.internal.wait

import com.kaspersky.kaspresso.internal.extensions.other.isAllowed
import com.kaspersky.kaspresso.logger.UiTestLogger

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
 * Waits for [timeoutMs] and invokes an [action].
 *
 * @param timeoutMs a time to wait in milliseconds.
 * @param logger a logger to log errors.
 * @param action an action that to be invoked.
 * @return [T] as it is a result of [action] invocation.
 */
internal fun <T> wait(
    timeoutMs: Long,
    logger: UiTestLogger,
    action: () -> T
): T {
    logger.i("Waiting for $timeoutMs ms")
    Thread.sleep(timeoutMs)
    return action.invoke()
}

internal fun <T> wait(
    timeoutMs: Long,
    logger: UiTestLogger,
    allowedExceptions: Set<Class<out Throwable>>,
    failureMessageSource: (Throwable) -> String?,
    action: (() -> T)?
): T? {
    logger.i("Waiting for $timeoutMs ms")
    return try {
        Thread.sleep(timeoutMs)
        action?.invoke()
    } catch (error: Throwable) {
        if (error.isAllowed(allowedExceptions)) {
            logger.e(failureMessageSource(error) ?: "An error occurred while waiting: ${error.javaClass.simpleName}")
            null
        } else {
            throw error
        }
    }
}
