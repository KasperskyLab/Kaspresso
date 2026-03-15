package androidx.test.espresso.web.assertion

import android.webkit.WebView
import com.kaspersky.kaspresso.interceptors.watcher.view.WebAssertionWatcherInterceptor
import org.hamcrest.Matcher

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
 * A proxy-wrapper of [WebAssertion] for [watcherInterceptors] calls.
 *
 * Uses [WebViewAssertions.ResultCheckingWebAssertion] class, that has package-local access in Espresso, so it has to be
 * in the same package.
 */
class WebAssertionProxy<E>(
    val webAssertion: WebAssertion<E>,
    val matcher: Matcher<*>,
    private val watcherInterceptors: List<WebAssertionWatcherInterceptor>
) : WebAssertion<E>(webAssertion.atom) {

    /**
     * Calls [watcherInterceptors] before [WebViewAssertions.ResultCheckingWebAssertion.checkResult] on wrapped
     * [webAssertion] is called.
     *
     * @param view a WebView that the Atom was evaluated on.
     * @param result a result of atom evaluation.
     */
    override fun checkResult(view: WebView?, result: E) {
        watcherInterceptors.forEach { it.intercept(this, view, result as Any) }
        (webAssertion as WebViewAssertions.ResultCheckingWebAssertion).checkResult(view, result)
    }
}
