package com.kaspersky.kaspresso.proxy

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import com.kaspersky.kaspresso.interceptors.watcher.view.ViewActionWatcherInterceptor
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
 * The proxy-wrapper of [ViewAction] for watcher interceptors calls.
 */
class ViewActionProxy(
    private val viewAction: ViewAction,
    private val watcherInterceptors: List<ViewActionWatcherInterceptor>
) : ViewAction {

    /**
     * Simply calls [ViewAction.getDescription] on wrapped [viewAction].
     *
     * @return a description of the view action.
     */
    override fun getDescription(): String = viewAction.description

    /**
     * Simply calls [ViewAction.getConstraints] on wrapped [viewAction].
     *
     * @return a matcher that will be tested prior to calling perform.
     */
    override fun getConstraints(): Matcher<View> = viewAction.constraints

    /**
     * Calls watcher interceptors before [ViewAction.perform] on wrapped [viewAction] is called.
     *
     * @param uiController the controller to use to interact with the UI.
     * @param view the view to act upon. never null.
     */
    override fun perform(uiController: UiController, view: View) {
        watcherInterceptors.forEach { it.intercept(viewAction, view) }
        viewAction.perform(uiController, view)
    }
}
