package com.kaspersky.kaspresso.interceptors.tolibrary.kakao

import androidx.test.espresso.web.assertion.WebAssertion
import androidx.test.espresso.web.assertion.WebAssertionProxy
import androidx.test.espresso.web.model.Atom
import androidx.test.espresso.web.sugar.Web
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.tolibrary.LibraryInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.view.AtomWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.view.WebAssertionWatcherInterceptor
import com.kaspersky.kaspresso.internal.extensions.espressoext.getMatcher
import com.kaspersky.kaspresso.proxy.AtomProxy

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
 * Kaspresso's implementation of Kakao's web interaction interceptor.
 */
internal class KakaoWebInterceptor(
    private val webBehaviorInterceptors: List<WebBehaviorInterceptor>,
    private val webAssertionWatcherInterceptors: List<WebAssertionWatcherInterceptor>,
    private val atomWatcherInterceptors: List<AtomWatcherInterceptor>
) : LibraryInterceptor<Web.WebInteraction<*>, WebAssertion<*>, Atom<*>> {

    /**
     * Folds all [Web.WebInteraction]'s one into another in the order from the first to the last with the actual
     * [androidx.test.espresso.web.sugar.Web.WebInteraction.check] call as the initial, and invokes the resulting
     * lambda.
     */
    override fun interceptCheck(interaction: Web.WebInteraction<*>, assertion: WebAssertion<*>) {
        webBehaviorInterceptors.fold(
            initial = {
                interaction.check(
                    WebAssertionProxy(assertion, interaction.getMatcher(), webAssertionWatcherInterceptors)
                )
            },
            operation = { acc, webBehaviorInterceptor: WebBehaviorInterceptor ->
                { webBehaviorInterceptor.intercept(interaction, acc) }
            }
        ).invoke()
    }

    /**
     * Folds all [Web.WebInteraction]'s one into another in the order from the first to the last with the actual
     * [androidx.test.espresso.web.sugar.Web.WebInteraction.perform] call as the initial, and invokes the resulting
     * lambda.
     */
    override fun interceptPerform(interaction: Web.WebInteraction<*>, action: Atom<*>) {
        webBehaviorInterceptors.fold(
            initial = {
                interaction.perform(
                    AtomProxy(action, interaction.getMatcher(), atomWatcherInterceptors)
                )
            },
            operation = { acc, webBehaviorInterceptor: WebBehaviorInterceptor ->
                { webBehaviorInterceptor.intercept(interaction, acc) }
            }
        ).invoke()
    }
}
