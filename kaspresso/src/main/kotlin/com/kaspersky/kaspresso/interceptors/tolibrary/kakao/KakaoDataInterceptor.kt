package com.kaspersky.kaspresso.interceptors.tolibrary.kakao

import androidx.test.espresso.DataInteraction
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import com.kaspersky.kaspresso.interceptors.behavior.DataBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.tolibrary.LibraryInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.view.ViewAssertionWatcherInterceptor
import com.kaspersky.kaspresso.proxy.DataAssertionProxy

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
 * Kaspresso's implementation of Kakao's data interaction interceptor.
 */
internal class KakaoDataInterceptor(
    private val dataBehaviorInterceptors: List<DataBehaviorInterceptor>,
    private val viewAssertionWatcherInterceptors: List<ViewAssertionWatcherInterceptor>
) : LibraryInterceptor<DataInteraction, ViewAssertion, ViewAction> {

    /**
     * Folds all [DataBehaviorInterceptor]'s one into another in the order from the first to the last with the actual
     * [androidx.test.espresso.DataInteraction.check] call as the initial, and invokes the resulting lambda.
     */
    override fun interceptCheck(interaction: DataInteraction, assertion: ViewAssertion) {
        dataBehaviorInterceptors.fold(
            initial = {
                interaction.check(
                    DataAssertionProxy(assertion, viewAssertionWatcherInterceptors)
                )
            },
            operation = { acc, dataBehaviorInterceptor: DataBehaviorInterceptor ->
                { dataBehaviorInterceptor.intercept(interaction, acc) }
            }
        ).invoke()
    }

    /**
     * Empty.
     */
    override fun interceptPerform(interaction: DataInteraction, action: ViewAction) = Unit
}
