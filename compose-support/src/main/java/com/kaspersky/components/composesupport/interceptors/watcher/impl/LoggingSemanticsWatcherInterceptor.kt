package com.kaspersky.components.composesupport.interceptors.watcher.impl

import com.kaspersky.components.composesupport.interceptors.watcher.SemanticsWatcherInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import io.github.kakaocup.compose.intercept.interaction.ComposeInteraction
import io.github.kakaocup.compose.intercept.operation.ComposeAction
import io.github.kakaocup.compose.intercept.operation.ComposeAssertion

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
 * The implementation of [SemanticsWatcherInterceptor] that logs info about [ComposeAssertion] or [ComposeAction]
 * and [ComposeInteraction] on which its activities are performing.
 */
class LoggingSemanticsWatcherInterceptor(
    private val logger: UiTestLogger
) : SemanticsWatcherInterceptor {

    /**
     * Writes info to [logger].
     *
     * @param interaction a Kautomator UiInteraction on which [assertion] is performed
     * @param assertion responsible for performing an activity (assertion) on the given [interaction]
     */
    override fun interceptCheck(interaction: ComposeInteraction, assertion: ComposeAssertion) {
        logger.i(
            "Operation: Check=${assertion.type}(description={${assertion.description}}).\n" +
                    "ComposeInteraction: $interaction."
        )
    }

    /**
     * Writes info to [logger].
     *
     * @param interaction a Kautomator UiInteraction on which [action] is performed
     * @param action responsible for performing an activity (action) on the given [interaction]
     */
    override fun interceptPerform(interaction: ComposeInteraction, action: ComposeAction) {
        logger.i(
            "Operation: Perform=${action.type}(description={${action.description}}).\n" +
                    "ComposeInteraction: $interaction."
        )
    }
}
