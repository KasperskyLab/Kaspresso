package com.kaspersky.components.kautomator.intercept.base

import com.kaspersky.components.kautomator.intercept.delegate.UiDelegate

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

interface UiInterceptable<Interaction, Assertion, Action> {
    val view: UiDelegate<Interaction, Assertion, Action>

    /**
     * Sets the interceptors for the instance.
     * Interceptors will be invoked on the interaction with the UiView.
     *
     * @param builder Builder of the interceptors
     *
     * @see UiInterceptor
     */
    fun intercept(builder: UiInterceptor.Builder<Interaction, Assertion, Action>.() -> Unit) {
        view.interceptor = UiInterceptor.Builder<Interaction, Assertion, Action>().apply(builder).build()
    }

    /**
     * Removes the interceptors from the instance.
     */
    fun reset() {
        view.interceptor = null
    }
}
