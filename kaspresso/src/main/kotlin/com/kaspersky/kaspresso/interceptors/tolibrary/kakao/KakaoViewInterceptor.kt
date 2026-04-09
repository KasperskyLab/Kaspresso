package com.kaspersky.kaspresso.interceptors.tolibrary.kakao

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.tolibrary.LibraryInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.view.ViewActionWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.view.ViewAssertionWatcherInterceptor
import com.kaspersky.kaspresso.proxy.ViewActionProxy
import com.kaspersky.kaspresso.proxy.ViewAssertionProxy

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
 * Kaspresso's implementation of Kakao's view interaction interceptor.
 */
internal class KakaoViewInterceptor(
    private val viewBehaviorInterceptors: List<ViewBehaviorInterceptor>,
    private val viewActionWatcherInterceptors: List<ViewActionWatcherInterceptor>,
    private val viewAssertionWatcherInterceptors: List<ViewAssertionWatcherInterceptor>
) : LibraryInterceptor<ViewInteraction, ViewAssertion, ViewAction> {

    /**
     * Folds all [ViewBehaviorInterceptor]'s one into another in the order from the first to the last with the actual
     * [androidx.test.espresso.ViewInteraction.check] call as the initial, and invokes the resulting lambda.
     */
    override fun interceptCheck(interaction: ViewInteraction, assertion: ViewAssertion) {
        viewBehaviorInterceptors.fold(
            initial = {
                interaction.check(
                    ViewAssertionProxy(assertion, viewAssertionWatcherInterceptors)
                )
            },
            operation = { acc, viewBehaviorInterceptor: ViewBehaviorInterceptor ->
                { viewBehaviorInterceptor.intercept(interaction, acc) }
            }
        ).invoke()
    }

    /**
     * Folds all [ViewBehaviorInterceptor]'s one into another in the order from the first to the last with the actual
     * [androidx.test.espresso.ViewInteraction.perform] call as the initial, and invokes the resulting lambda.
     */
    override fun interceptPerform(interaction: ViewInteraction, action: ViewAction) {
        viewBehaviorInterceptors.fold(
            initial = {
                interaction.perform(
                    ViewActionProxy(action, viewActionWatcherInterceptors)
                )
            },
            operation = { acc, viewBehaviorInterceptor: ViewBehaviorInterceptor ->
                { viewBehaviorInterceptor.intercept(interaction, acc) }
            }
        ).invoke()
    }
}
