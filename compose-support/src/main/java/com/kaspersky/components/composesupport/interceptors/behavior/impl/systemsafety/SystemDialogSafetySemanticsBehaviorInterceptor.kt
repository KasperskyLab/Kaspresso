package com.kaspersky.components.composesupport.interceptors.behavior.impl.systemsafety

import com.kaspersky.components.composesupport.interceptors.behavior.SemanticsBehaviorInterceptor
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.instrumental.InstrumentalDependencyProvider
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.SystemDialogsSafetyParams
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProvider
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProviderImpl
import io.github.kakaocup.compose.intercept.interaction.ComposeInteraction
import io.github.kakaocup.compose.intercept.operation.ComposeAction
import io.github.kakaocup.compose.intercept.operation.ComposeAssertion

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
 * The implementation of [SemanticsBehaviorInterceptor] and [SystemDialogSafetyProvider] interfaces.
 * Provides system dialog safety functionality for [ComposeInteraction.perform] and [ComposeInteraction.check] calls.
 */
class SystemDialogSafetySemanticsBehaviorInterceptor(
    logger: UiTestLogger,
    instrumentalDependencyProvider: InstrumentalDependencyProvider,
    adbServer: AdbServer,
    systemDialogsSafetyParams: SystemDialogsSafetyParams
) : SemanticsBehaviorInterceptor,
    SystemDialogSafetyProvider by SystemDialogSafetyProviderImpl(logger, instrumentalDependencyProvider, adbServer, systemDialogsSafetyParams) {

    /**
     * Wraps the given [activity] invocation with the system dialog safety.
     *
     * @param interaction the intercepted [ComposeInteraction].
     * @param assertion the intercepted [ComposeAssertion].
     * @param activity the activity to invoke.
     */
    override fun <T> interceptCheck(
        interaction: ComposeInteraction,
        assertion: ComposeAssertion,
        activity: () -> T
    ): T = passSystemDialogs(activity)

    /**
     * Wraps the given [activity] invocation with the system dialog safety.
     *
     * @param interaction the intercepted [ComposeInteraction].
     * @param action the intercepted [ComposeAction].
     * @param activity the activity to invoke.
     */
    override fun <T> interceptPerform(
        interaction: ComposeInteraction,
        action: ComposeAction,
        activity: () -> T
    ): T = passSystemDialogs(activity)
}
