@file:Suppress("unused")
package com.kaspersky.components.kautomator.system

import com.kaspersky.components.kautomator.intercept.delegate.UiDeviceInteractionDelegate
import com.kaspersky.components.kautomator.intercept.operation.UiOperationType
import com.kaspersky.components.kautomator.system.UiSystemAssertions.UiSystemAssertionType.IS_SCREEN_ON

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
 * Interface with common assertions providing by UiAutomator and executing in the System
 *
 * Provides basic assertions that can be checked everywhere
 *
 * @property view UiDeviceDelegate on which all actions are checked
 */
interface UiSystemAssertions {

    val view: UiDeviceInteractionDelegate

    fun isScreenOn() {
        view.check(IS_SCREEN_ON) {
            checkAssertAction(IS_SCREEN_ON) { isScreenOn }
        }
    }

    private fun checkAssertAction(methodName: UiOperationType, action: () -> Boolean) {
        try {
            val result = action.invoke()
            if (!result) throw AssertionError("$methodName method in UiAutomator hasn't been checked")
        } catch (exception: Exception) {
            throw AssertionError("$methodName method in UiAutomator hasn't been checked")
        }
    }

    enum class UiSystemAssertionType : UiOperationType {
        IS_SCREEN_ON
    }
}
