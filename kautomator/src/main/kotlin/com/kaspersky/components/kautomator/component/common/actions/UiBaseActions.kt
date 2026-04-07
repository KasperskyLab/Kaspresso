@file:Suppress("unused")
package com.kaspersky.components.kautomator.component.common.actions

import com.kaspersky.components.kautomator.intercept.delegate.UiObjectInteractionDelegate
import com.kaspersky.components.kautomator.intercept.operation.UiOperationType

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
 * Base interface for performing actions on UiAutomator view
 *
 * Provides a lot of basic action methods, such as click(), etc.
 *
 * @see com.kaspersky.components.kautomator.component.edit.UiEditableActions
 */
interface UiBaseActions {
    val view: UiObjectInteractionDelegate

    /**
     * Performs click on view
     */
    fun click() {
        view.perform(UiBaseActionType.CLICK) { click() }
    }

    /**
     * Performs double click on view
     */
    fun doubleClick() {
        view.perform(UiBaseActionType.DOUBLE_CLICK) {
            click()
            click()
        }
    }

    /**
     * Performs long click on view
     */
    fun longClick() {
        view.perform(UiBaseActionType.LONG_CLICK) { longClick() }
    }

    enum class UiBaseActionType : UiOperationType {
        CLICK,
        DOUBLE_CLICK,
        LONG_CLICK
    }
}
