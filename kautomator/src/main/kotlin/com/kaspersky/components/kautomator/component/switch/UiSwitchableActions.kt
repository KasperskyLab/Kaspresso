@file:Suppress("unused")
package com.kaspersky.components.kautomator.component.switch

import com.kaspersky.components.kautomator.component.common.actions.UiBaseActions
import com.kaspersky.components.kautomator.component.switch.UiSwitchableActions.SwitchableUiActionType.SET_CHECKED
import com.kaspersky.components.kautomator.component.switch.UiSwitchableActions.SwitchableUiActionType.SWITCH
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
 * Provides switchable actions for UiSwitch
 */
interface UiSwitchableActions : UiBaseActions {

    enum class Direction { RIGHT, LEFT }

    /**
     * Moves the thumb of the switch to the right
     *
     * @param direction for the thumb to move
     */
    fun swipeSwitchThumb(direction: Direction) {
        view.perform(SWITCH, "direction=$direction") {
            val uiDirection: androidx.test.uiautomator.Direction =
                if (direction == Direction.RIGHT)
                    androidx.test.uiautomator.Direction.RIGHT
                else
                    androidx.test.uiautomator.Direction.LEFT
            swipe(uiDirection, 1f)
        }
    }

    fun setChecked(isChecked: Boolean) {
        view.perform(SET_CHECKED, "setChecked=$isChecked") {
            if (isChecked() != isChecked) {
                click()
            }
        }
    }

    enum class SwitchableUiActionType : UiOperationType {
        SWITCH, SET_CHECKED
    }
}
