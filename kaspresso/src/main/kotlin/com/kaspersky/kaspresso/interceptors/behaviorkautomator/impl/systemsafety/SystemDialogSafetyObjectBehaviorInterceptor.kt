package com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.systemsafety

import com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAssertion
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.instrumental.InstrumentalDependencyProvider
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.ObjectBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.SystemDialogsSafetyParams
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProvider
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProviderImpl

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
 * The implementation of [ObjectBehaviorInterceptor] and [SystemDialogSafetyProvider] interfaces.
 * Provides system dialog safety functionality for [UiObjectInteraction.perform] and [UiObjectInteraction.check] calls.
 */
class SystemDialogSafetyObjectBehaviorInterceptor(
    logger: UiTestLogger,
    instrumentalDependencyProvider: InstrumentalDependencyProvider,
    adbServer: AdbServer,
    systemDialogsSafetyParams: SystemDialogsSafetyParams
) : ObjectBehaviorInterceptor,
    SystemDialogSafetyProvider by SystemDialogSafetyProviderImpl(logger, instrumentalDependencyProvider, adbServer, systemDialogsSafetyParams) {

    /**
     * Wraps the given [activity] invocation with the system dialog safety.
     *
     * @param interaction the intercepted [UiObjectInteraction].
     * @param assertion the intercepted [UiObjectAssertion].
     * @param activity the activity to invoke.
     */
    override fun <T> interceptCheck(
        interaction: UiObjectInteraction,
        assertion: UiObjectAssertion,
        activity: () -> T
    ): T = passSystemDialogs(activity)

    /**
     * Wraps the given [activity] invocation with the system dialog safety.
     *
     * @param interaction the intercepted [UiObjectInteraction].
     * @param action the intercepted [UiObjectAction].
     * @param activity the activity to invoke.
     */
    override fun <T> interceptPerform(
        interaction: UiObjectInteraction,
        action: UiObjectAction,
        activity: () -> T
    ): T = passSystemDialogs(activity)
}
