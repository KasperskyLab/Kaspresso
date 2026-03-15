package com.kaspersky.kaspresso.compose

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import io.github.kakaocup.kakao.common.actions.BaseActions
import io.github.kakaocup.kakao.common.assertions.BaseAssertions
import io.github.kakaocup.kakao.intercept.Interceptable
import com.kaspersky.components.kautomator.component.common.actions.UiBaseActions
import com.kaspersky.components.kautomator.component.common.assertions.UiBaseAssertions
import com.kaspersky.components.kautomator.intercept.base.UiInterceptable
import com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAssertion
import com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack
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
 * The interface to provide composing actions and assertions functionality.
 */
interface ComposeProvider {

    /**
     * Composes a [block] of actions with their views to invoke on in one composite action that succeeds if at least
     * one of it's parts succeeds.
     * Please, be aware of `or` sections are executing flakySafely!
     *
     * @param timeoutMs the timeout during which attempts to execute `or` sections will be made.
     * @param intervalMs the interval at which attempts to execute `or` sections will be made.
     * @param allowedExceptions the set of exceptions that allow to continue an attempt of `or` sections execution.
     * @param block the actions to compose.
     */
    fun compose(
        timeoutMs: Long? = null,
        intervalMs: Long? = null,
        allowedExceptions: Set<Class<out Throwable>>? = null,
        block: ActionsOnElementsPack.() -> Unit
    )

    /**
     * Composes a [block] of actions with their views to invoke on in one composite action that succeeds if at least
     * one of it's parts succeeds.
     * Please, be aware of `or` sections are executing without flakySafely mechanism
     *     even though there may be flakySafely interceptors in your Kaspresso settings!
     *
     * @param block the actions to compose.
     */
    fun unsafeCompose(block: ActionsOnElementsPack.() -> Unit)

    /**
     * Composes a [block] of actions on the given view of type [Type] in one composite action that succeeds if at least
     * one of it's parts succeeds.
     * Please, be aware of `or` sections are executing flakySafely!
     *
     * @param timeoutMs the timeout during which attempts to execute `or` sections will be made.
     * @param intervalMs the interval at which attempts to execute `or` sections will be made.
     * @param allowedExceptions the set of exceptions that allow to continue an attempt of `or` sections execution.
     * @param block the actions to compose.
     */
    fun <Type> Type.compose(
        timeoutMs: Long? = null,
        intervalMs: Long? = null,
        allowedExceptions: Set<Class<out Throwable>>? = null,
        block: ActionsPack<Type>.() -> Unit
    )
            where Type : BaseActions, Type : BaseAssertions,
                  Type : Interceptable<ViewInteraction, ViewAssertion, ViewAction>

    /**
     * Composes a [block] of actions with their views to invoke on in one composite action that succeeds if at least
     * one of it's parts succeeds.
     * Please, be aware of `or` sections are executing without flakySafely mechanism
     *     even though there may be flakySafely interceptors in your Kaspresso settings!
     *
     * @param block the actions to compose.
     */
    fun <Type> Type.unsafeCompose(block: ActionsPack<Type>.() -> Unit)
            where Type : BaseActions, Type : BaseAssertions,
                  Type : Interceptable<ViewInteraction, ViewAssertion, ViewAction>

    /**
     * Composes a [block] of actions on the given view of type [Type] in one composite action that succeeds if at least
     * one of it's parts succeeds.
     * Please, be aware of `or` sections are executing flakySafely!
     *
     * @param timeoutMs the timeout during which attempts to execute `or` sections will be made.
     * @param intervalMs the interval at which attempts to execute `or` sections will be made.
     * @param allowedExceptions the set of exceptions that allow to continue an attempt of `or` sections execution.
     * @param block the actions to compose.
     */
    fun <Type> Type.compose(
        timeoutMs: Long? = null,
        intervalMs: Long? = null,
        allowedExceptions: Set<Class<out Throwable>>? = null,
        block: ActionsPack<Type>.() -> Unit
    )
            where Type : UiBaseActions, Type : UiBaseAssertions,
                  Type : UiInterceptable<UiObjectInteraction, UiObjectAssertion, UiObjectAction>

    /**
     * Composes a [block] of actions with their views to invoke on in one composite action that succeeds if at least
     * one of it's parts succeeds.
     * Please, be aware of `or` sections are executing without flakySafely mechanism
     *     even though there may be flakySafely interceptors in your Kaspresso settings!
     *
     * @param block the actions to compose.
     */
    fun <Type> Type.unsafeCompose(block: ActionsPack<Type>.() -> Unit)
            where Type : UiBaseActions, Type : UiBaseAssertions,
                  Type : UiInterceptable<UiObjectInteraction, UiObjectAssertion, UiObjectAction>
}
