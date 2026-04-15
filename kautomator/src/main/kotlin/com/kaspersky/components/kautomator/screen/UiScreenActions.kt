@file:Suppress("unused")
package com.kaspersky.components.kautomator.screen

import com.kaspersky.components.kautomator.intercept.delegate.UiDeviceInteractionDelegate
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
 * Interface with common actions for all UiAutomator screens
 *
 * Provides basic actions that can be performed on each and every screen
 *
 * @property view UiDeviceDelegate on which all actions are performed
 */
interface UiScreenActions {

    val view: UiDeviceInteractionDelegate

    /**
     * Simulates a short press on the BACK button.
     */
    fun pressBack() {
        view.perform(UiScreenActionType.PRESS_BACK) { pressBack() }
    }

    /**
     * Waits for window update for specified package name
     */
    fun waitForWindowUpdate(packageName: String, timeout: Long) {
        view.perform(UiScreenActionType.WAIT_FOR_WINDOW_UPDATE) {
            waitForWindowUpdate(packageName, timeout)
        }
    }

    /**
     * Waits for window update
     */
    fun waitForWindowUpdate(timeout: Long) {
        view.perform(UiScreenActionType.WAIT_FOR_WINDOW_UPDATE) {
            waitForWindowUpdate(timeout)
        }
    }

    enum class UiScreenActionType : UiOperationType {
        PRESS_BACK, WAIT_FOR_WINDOW_UPDATE
    }
}
