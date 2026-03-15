package com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.autoscroll

import com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAssertion
import com.kaspersky.kaspresso.autoscroll.AutoScrollProvider
import com.kaspersky.kaspresso.autoscroll.ObjectAutoScrollProviderImpl
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.ObjectBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.AutoScrollParams

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
 * The implementation of [ObjectBehaviorInterceptor] and [AutoScrollProvider] interfaces.
 * Provides autoscroll on failure functionality for [UiObjectInteraction.perform] and [UiObjectInteraction.check] calls.
 */
class AutoScrollObjectBehaviorInterceptor(
    logger: UiTestLogger,
    autoScrollParams: AutoScrollParams
) : ObjectBehaviorInterceptor,
    AutoScrollProvider<UiObjectInteraction> by ObjectAutoScrollProviderImpl(logger, autoScrollParams) {

    /**
     * Wraps the given [assertion] invocation with the autoscrolling on failure.
     *
     * @param interaction the intercepted [UiObjectInteraction].
     * @param assertion the assertion to invoke.
     */
    override fun <T> interceptCheck(
        interaction: UiObjectInteraction,
        assertion: UiObjectAssertion,
        activity: () -> T
    ): T = withAutoScroll(interaction, activity)

    /**
     * Wraps the given [action] invocation with the autoscrolling on failure.
     *
     * @param interaction the intercepted [UiObjectInteraction].
     * @param action the assertion to invoke.
     */
    override fun <T> interceptPerform(
        interaction: UiObjectInteraction,
        action: UiObjectAction,
        activity: () -> T
    ): T = withAutoScroll(interaction, activity)
}
