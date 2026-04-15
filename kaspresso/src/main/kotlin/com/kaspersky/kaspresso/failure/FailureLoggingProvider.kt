package com.kaspersky.kaspresso.failure

import android.view.View
import org.hamcrest.Matcher

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
 * An interface to provide the logging failures functionality.
 */
interface FailureLoggingProvider {

    /**
     * Invokes the given [action] and logs if it fails.
     *
     * @param action the action to invoke.
     *
     * @return the result of the [action] invocation.
     */
    fun <T> withLoggingOnFailure(action: () -> T): T

    /**
     * Logs the [error]'s stacktrace.
     *
     * @param error the error to get stacktrace from.
     */
    fun logStackTrace(error: Throwable)

    /**
     * Logs the [error] description got by [viewMatcher] and throws the [error].
     *
     * @param error the error to be described.
     * @param viewMatcher the view matcher to get description from.
     */
    fun logDescriptionAndThrow(error: Throwable?, viewMatcher: Matcher<View>?)
}
