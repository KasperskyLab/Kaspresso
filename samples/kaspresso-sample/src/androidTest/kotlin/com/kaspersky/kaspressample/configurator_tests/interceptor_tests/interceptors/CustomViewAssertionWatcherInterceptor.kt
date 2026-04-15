package com.kaspersky.kaspressample.configurator_tests.interceptor_tests.interceptors

import android.view.View
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import com.kaspersky.kaspressample.configurator_tests.interceptor_tests.helpers.CheckCustomInterceptorsStorage
import com.kaspersky.kaspresso.interceptors.watcher.view.ViewAssertionWatcherInterceptor

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

class CustomViewAssertionWatcherInterceptor : ViewAssertionWatcherInterceptor {

    private var previousViewAssertion: ViewAssertion? = null
    private var previousView: View? = null

    override fun intercept(viewAssertion: ViewAssertion, view: View?, exception: NoMatchingViewException?) {
        // we don't count retry attempts to execute an assertion, but in the example they are
        if (previousView?.id == view?.id && previousViewAssertion.toString() == viewAssertion.toString()) {
            return
        }
        CheckCustomInterceptorsStorage.putToViewAssertionInterceptorCheckList()
        previousView = view
        previousViewAssertion = viewAssertion
    }
}
