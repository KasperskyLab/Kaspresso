package com.kaspersky.kaspresso.interceptors.watcher.view

import android.webkit.WebView
import androidx.test.espresso.web.assertion.WebAssertionProxy

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
 * The interface for all atom interceptors, used in [WebAssertionProxy].
 */
interface WebAssertionWatcherInterceptor {

    /**
     * Called to do some stuff before [androidx.test.espresso.web.assertion.WebAssertion.checkResult] is actually
     * called.
     *
     * @param webAssertionProxy a proxy-wrapper of [androidx.test.espresso.web.assertion.WebAssertion] for
     *      interceptors calls.
     * @param view an Android [View], on which [androidx.test.espresso.web.assertion.WebAssertion] is performed.
     * @param result a result of [androidx.test.espresso.web.assertion.WebAssertion].
     */
    fun intercept(webAssertionProxy: WebAssertionProxy<*>, view: WebView?, result: Any)
}
