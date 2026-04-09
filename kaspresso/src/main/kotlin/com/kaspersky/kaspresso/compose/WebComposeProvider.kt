package com.kaspersky.kaspresso.compose

import io.github.kakaocup.kakao.web.WebElementBuilder
import com.kaspersky.kaspresso.compose.pack.ActionsOnWebElementsPack
import com.kaspersky.kaspresso.compose.pack.ActionsPack

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
 * The interface to provide the composing actions and assertions on web views functionality.
 */
interface WebComposeProvider {

    /**
     * Composes a [block] of actions with their web views to invoke on in one composite action that succeeds if at least
     * one of it's parts succeeds.
     * Please, be aware of `or` sections are executing flakySafely!
     *
     * @param timeoutMs the timeout during which attempts to execute `or` sections will be made.
     * @param intervalMs the interval at which attempts to execute `or` sections will be made.
     * @param allowedExceptions the set of exceptions that allow to continue an attempt of `or` sections execution.
     * @param block the actions to compose.
     */
    fun WebElementBuilder.compose(
        timeoutMs: Long? = null,
        intervalMs: Long? = null,
        allowedExceptions: Set<Class<out Throwable>>? = null,
        block: ActionsOnWebElementsPack.() -> Unit
    )

    /**
     * Composes a [block] of actions with their web views to invoke on in one composite action that succeeds if at least
     * one of it's parts succeeds.
     * Please, be aware of `or` sections are executing without flakySafely mechanism
     *     even though there may be flakySafely interceptors in your Kaspresso settings!
     *
     * @param block the actions to compose.
     */
    fun WebElementBuilder.unsafeCompose(block: ActionsOnWebElementsPack.() -> Unit)

    /**
     * Composes a [block] of actions with their web views to invoke on in one composite action that succeeds if at least
     * one of it's parts succeeds.
     * Please, be aware of `or` sections are executing flakySafely!
     *
     * @param timeoutMs the timeout during which attempts to execute `or` sections will be made.
     * @param intervalMs the interval at which attempts to execute `or` sections will be made.
     * @param allowedExceptions the set of exceptions that allow to continue an attempt of `or` sections execution.
     * @param block the actions to compose.
     */
    fun WebElementBuilder.KWebInteraction.compose(
        webElementBuilder: WebElementBuilder,
        timeoutMs: Long? = null,
        intervalMs: Long? = null,
        allowedExceptions: Set<Class<out Throwable>>? = null,
        block: ActionsPack<WebElementBuilder.KWebInteraction>.() -> Unit
    )

    /**
     * Composes a [block] of actions with their web views to invoke on in one composite action that succeeds if at least
     * one of it's parts succeeds.
     * Please, be aware of `or` sections are executing without flakySafely mechanism
     *     even though there may be flakySafely interceptors in your Kaspresso settings!
     *
     * @param block the actions to compose.
     */
    fun WebElementBuilder.KWebInteraction.unsafeCompose(
        webElementBuilder: WebElementBuilder,
        block: ActionsPack<WebElementBuilder.KWebInteraction>.() -> Unit
    )
}
