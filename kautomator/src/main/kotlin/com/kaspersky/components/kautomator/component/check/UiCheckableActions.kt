@file:Suppress("unused")
package com.kaspersky.components.kautomator.component.check

import com.kaspersky.components.kautomator.component.check.UiCheckableActions.UiCheckableActionType.SET_CHECKED
import com.kaspersky.components.kautomator.component.common.actions.UiBaseActions
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
 * Provides actions for checkable views
 */
interface UiCheckableActions : UiBaseActions {

    /**
     * Sets checked state of the view
     *
     * @param checked True if checked, false otherwise
     */
    fun setChecked(checked: Boolean) {
        view.perform(SET_CHECKED, "checked=$checked") {
            if (isChecked != checked) click()
        }
    }

    enum class UiCheckableActionType : UiOperationType {
        SET_CHECKED
    }
}
