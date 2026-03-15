package com.kaspersky.kaspresso.interceptors.watcher.view

import android.view.View
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion

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
 * The interface for all view action interceptors, used in [com.kaspersky.kaspresso.proxy.ViewAssertionProxy].
 */
interface ViewAssertionWatcherInterceptor {

    /**
     * Called to do some stuff before [androidx.test.espresso.ViewAssertion.check] is actually called.
     *
     * @param viewAssertion responsible for performing assertions on a [View] element.
     * @param view an Android [View], on which [viewAssertion] is performed.
     * @param exception indicates that a given matcher did not match any elements in the view hierarchy.
     */
    fun intercept(viewAssertion: ViewAssertion, view: View?, exception: NoMatchingViewException?)
}
