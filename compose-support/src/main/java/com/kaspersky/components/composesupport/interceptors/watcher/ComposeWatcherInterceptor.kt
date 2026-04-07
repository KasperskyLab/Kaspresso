package com.kaspersky.components.composesupport.interceptors.watcher

import io.github.kakaocup.compose.intercept.interaction.ComposeInteraction

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
 * The interface for all interceptors that are watching the default interaction in Kautomator.
 */
interface ComposeWatcherInterceptor<Interaction, Assertion, Action> {

    /**
     * Called to do some stuff before [ComposeInteraction.check] is actually called.
     *
     * @param interaction a Kakao-Compose ComposeInteraction on which [assertion] is performed
     * @param assertion responsible for performing an activity (assertion) on the given [interaction]
     */
    fun interceptCheck(interaction: Interaction, assertion: Assertion)

    /**
     * Called to do some stuff before [ComposeInteraction.perform] is actually called.
     *
     * @param interaction a Kakao-Compose ComposeInteraction on which [action] is performed
     * @param action responsible for performing an activity (action) on the given [interaction]
     */
    fun interceptPerform(interaction: Interaction, action: Action)
}
