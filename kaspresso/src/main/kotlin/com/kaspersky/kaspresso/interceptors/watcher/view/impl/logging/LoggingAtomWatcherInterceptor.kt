package com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging

import androidx.test.espresso.web.model.Atom
import androidx.test.espresso.web.model.Evaluation
import androidx.test.espresso.web.webdriver.describeTo
import com.kaspersky.kaspresso.interceptors.watcher.view.AtomWatcherInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.proxy.AtomProxy
import org.hamcrest.StringDescription

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
 * The implementation of [AtomWatcherInterceptor] that logs info about web action.
 */
class LoggingAtomWatcherInterceptor(
    private val logger: UiTestLogger
) : AtomWatcherInterceptor {

    /**
     * Writes info to [logger].
     *
     * @param evaluation represents the results of a Javascript execution.
     */
    override fun intercept(atomProxy: AtomProxy<*>, evaluation: Evaluation?) {
        logger.i(getFullAtomDescription(atomProxy, evaluation))
    }

    /**
     * @return a string description of [Atom].
     */
    private fun getFullAtomDescription(atomProxy: AtomProxy<*>, evaluation: Evaluation?): String {
        return StringBuilder("web action")
            .apply {
                atomProxy.atom.describeTo(this, evaluation)
            }
            .apply {
                append(" on webview ")
                atomProxy.matcher.describeTo(StringDescription(this))
            }
            .toString()
    }
}
