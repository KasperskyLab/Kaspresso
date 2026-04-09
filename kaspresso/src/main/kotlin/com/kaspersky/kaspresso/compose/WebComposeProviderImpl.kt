package com.kaspersky.kaspresso.compose

import io.github.kakaocup.kakao.web.WebElementBuilder
import com.kaspersky.kaspresso.compose.pack.ActionsOnWebElementsPack
import com.kaspersky.kaspresso.compose.pack.ActionsPack
import com.kaspersky.kaspresso.kaspresso.Kaspresso

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
 * The implementation of the [WebComposeProvider] interface.
 */
class WebComposeProviderImpl(
    private val kaspresso: Kaspresso
) : WebComposeProvider {

    private val composeExecutor = ComposeExecutor(kaspresso)

    override fun WebElementBuilder.compose(
        timeoutMs: Long?,
        intervalMs: Long?,
        allowedExceptions: Set<Class<out Throwable>>?,
        block: ActionsOnWebElementsPack.() -> Unit
    ) {
        val composeBranches = ActionsOnWebElementsPack(this).apply(block).build()
        composeExecutor.executeComposeBranches(timeoutMs, intervalMs, allowedExceptions, composeBranches)
    }

    override fun WebElementBuilder.unsafeCompose(block: ActionsOnWebElementsPack.() -> Unit) {
        val composeBranches = ActionsOnWebElementsPack(this).apply(block).build()
        composeExecutor.executeComposeBranchesUnsafely(composeBranches)
    }

    override fun WebElementBuilder.KWebInteraction.compose(
        webElementBuilder: WebElementBuilder,
        timeoutMs: Long?,
        intervalMs: Long?,
        allowedExceptions: Set<Class<out Throwable>>?,
        block: ActionsPack<WebElementBuilder.KWebInteraction>.() -> Unit
    ) {
        val composeBranches = ActionsPack(this).apply(block).build()
        composeExecutor.executeComposeBranches(timeoutMs, intervalMs, allowedExceptions, composeBranches)
    }

    override fun WebElementBuilder.KWebInteraction.unsafeCompose(
        webElementBuilder: WebElementBuilder,
        block: ActionsPack<WebElementBuilder.KWebInteraction>.() -> Unit
    ) {
        val composeBranches = ActionsPack(this).apply(block).build()
        composeExecutor.executeComposeBranchesUnsafely(composeBranches)
    }
}
