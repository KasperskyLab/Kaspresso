package com.kaspersky.components.composesupport.interceptors.behavior.impl.elementloader

import com.kaspersky.kaspresso.elementloader.ElementLoaderProvider
import com.kaspersky.kaspresso.elementloader.ElementLoaderProviderImpl
import com.kaspersky.components.composesupport.interceptors.behavior.SemanticsBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.ElementLoaderParams
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
 * The implementation of [SemanticsBehaviorInterceptor] and [ElementLoaderProvider] interfaces.
 * Provides element reloading on failure functionality for [ComposeInteraction.perform] and [ComposeInteraction.check] calls.
 */
class ElementLoaderSemanticsBehaviorInterceptor(
    logger: UiTestLogger,
    params: ElementLoaderParams
) : SemanticsBehaviorInterceptor,
    ElementLoaderProvider by ElementLoaderProviderImpl(logger, params) {

    /**
     * Wraps the given [assertion] invocation with the element reloading on failure.
     *
     * @param interaction the intercepted [ComposeInteraction].
     * @param assertion the assertion to invoke.
     */
    override fun <T> interceptCheck(
        interaction: ComposeInteraction,
        assertion: ComposeAssertion,
        activity: () -> T
    ): T = passAction(
        elementLoader = { interaction.reFindNode() },
        action = activity
    )

    /**
     * Wraps the given [action] invocation with the element reloading on failure.
     *
     * @param interaction the intercepted [ComposeInteraction].
     * @param action the assertion to invoke.
     */
    override fun <T> interceptPerform(
        interaction: ComposeInteraction,
        action: ComposeAction,
        activity: () -> T
    ): T = passAction(
        elementLoader = { interaction.reFindNode() },
        action = activity
    )
}
