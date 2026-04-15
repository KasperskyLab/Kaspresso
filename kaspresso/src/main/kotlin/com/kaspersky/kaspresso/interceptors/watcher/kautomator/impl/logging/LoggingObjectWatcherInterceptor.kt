package com.kaspersky.kaspresso.interceptors.watcher.kautomator.impl.logging

import com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAssertion
import com.kaspersky.kaspresso.interceptors.watcher.kautomator.ObjectWatcherInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger

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
 * The implementation of [ObjectWatcherInterceptor] that logs info about [UiObjectAssertion] or [UiObjectAction]
 * and [UiObjectInteraction] on which its activities are performing.
 */
class LoggingObjectWatcherInterceptor(
    private val logger: UiTestLogger
) : ObjectWatcherInterceptor {

    /**
     * Writes info to [logger].
     *
     * @param interaction a Kautomator UiInteraction on which [assertion] is performed
     * @param assertion responsible for performing an activity (assertion) on the given [interaction]
     */
    override fun interceptCheck(interaction: UiObjectInteraction, assertion: UiObjectAssertion) {
        logger.i(
            "The object: ${interaction.description}. " +
                    "The operation: Check=${assertion.type}(description={${assertion.description}}. " +
                    "Additional info: the object was found by selectors=${interaction.selector.selectors} and index=${interaction.selector.selectors}"
        )
    }

    /**
     * Writes info to [logger].
     *
     * @param interaction a Kautomator UiInteraction on which [action] is performed
     * @param action responsible for performing an activity (action) on the given [interaction]
     */
    override fun interceptPerform(interaction: UiObjectInteraction, action: UiObjectAction) {
        logger.i(
            "The object: ${interaction.description}. " +
                    "The operation: Action=${action.type}(description={${action.description}}. " +
                    "Additional info: the object was found by selectors=${interaction.selector.selectors} and index=${interaction.selector.selectors}"
        )
    }
}
