package com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.failure

import com.kaspersky.components.kautomator.intercept.interaction.UiDeviceInteraction
import com.kaspersky.components.kautomator.intercept.operation.UiDeviceAction
import com.kaspersky.components.kautomator.intercept.operation.UiDeviceAssertion
import com.kaspersky.kaspresso.failure.FailureLoggingProvider
import com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.DeviceBehaviorInterceptor
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
 * The implementation of [DeviceBehaviorInterceptor] and [FailureLoggingProvider] interfaces.
 * Provides failure logging functionality for [UiDeviceInteraction.perform] and [UiDeviceInteraction.check] calls.
 *
 * Important!
 * By default, the interceptor is not used in Kaspresso because this one pollutes logs by error messages that may confuse a user.
 * If you desire to change result log (especially in case of an error) we recommend to use [FailureLoggingProvider] directly.
 */
class FailureLoggingDeviceBehaviorInterceptor(
    logger: UiTestLogger
) : DeviceBehaviorInterceptor,
    FailureLoggingProvider by FailureLoggingProviderImpl(logger) {

    /**
     * Wraps the given [activity] invocation with the failure logging.
     *
     * @param interaction the intercepted [UiDeviceInteraction].
     * @param assertion the intercepted [UiDeviceAssertion].
     * @param activity the activity to invoke.
     */
    override fun <T> interceptCheck(
        interaction: UiDeviceInteraction,
        assertion: UiDeviceAssertion,
        activity: () -> T
    ): T = withLoggingOnFailure(action = activity)

    /**
     * Wraps the given [activity] invocation with the failure logging.
     *
     * @param interaction the intercepted [UiDeviceInteraction].
     * @param action the intercepted [UiDeviceAction].
     * @param activity the activity to invoke.
     */
    override fun <T> interceptPerform(
        interaction: UiDeviceInteraction,
        action: UiDeviceAction,
        activity: () -> T
    ): T = withLoggingOnFailure(action = activity)
}
