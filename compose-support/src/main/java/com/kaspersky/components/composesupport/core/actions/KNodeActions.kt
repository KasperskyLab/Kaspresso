package com.kaspersky.components.composesupport.core.actions

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.doubleClick
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.performTouchInput
import com.kaspersky.components.composesupport.core.actions.options.DoubleClickConfig
import com.kaspersky.components.composesupport.core.actions.options.LongClickConfig
import io.github.kakaocup.compose.intercept.delegate.ComposeDelegate
import io.github.kakaocup.compose.intercept.operation.ComposeOperationType

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

interface KNodeActions {
    val delegate: ComposeDelegate

    /**
     * Performs a double click action on the element represented by the given semantics node.
     */
    fun doubleClick(doubleClickConfig: DoubleClickConfig? = null) {
        delegate.perform(ComposeKNodeActionType.PERFORM_DOUBLE_CLICK) {
            performTouchInput {
                doubleClick(
                    position = Offset(doubleClickConfig?.xOffset ?: centerX, doubleClickConfig?.yOffset ?: centerY),
                    delayMillis = doubleClickConfig?.delayMs ?: ((viewConfiguration.doubleTapMinTimeMillis + viewConfiguration.doubleTapTimeoutMillis) / 2)
                )
            }
        }
    }

    /**
     * Performs a long click action on the element represented by the given semantics node.
     */
    fun longClick(longClickConfig: LongClickConfig? = null) {
        delegate.perform(ComposeKNodeActionType.PERFORM_LONG_CLICK) {
            performTouchInput {
                longClick(
                    position = Offset(longClickConfig?.xOffset ?: centerX, longClickConfig?.yOffset ?: centerY),
                    durationMillis = longClickConfig?.durationMs ?: ((viewConfiguration.doubleTapMinTimeMillis + viewConfiguration.doubleTapTimeoutMillis) / 2)
                )
            }
        }
    }

    enum class ComposeKNodeActionType : ComposeOperationType {
        PERFORM_DOUBLE_CLICK,
        PERFORM_LONG_CLICK
    }
}
