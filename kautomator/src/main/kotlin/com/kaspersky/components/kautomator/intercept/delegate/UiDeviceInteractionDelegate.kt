package com.kaspersky.components.kautomator.intercept.delegate

import androidx.test.uiautomator.UiDevice
import com.kaspersky.components.kautomator.KautomatorConfigurator
import com.kaspersky.components.kautomator.intercept.base.UiInterceptor
import com.kaspersky.components.kautomator.intercept.interaction.UiDeviceInteraction
import com.kaspersky.components.kautomator.intercept.operation.UiDeviceAction
import com.kaspersky.components.kautomator.intercept.operation.UiDeviceAssertion
import com.kaspersky.components.kautomator.intercept.operation.UiOperationBaseImpl
import com.kaspersky.components.kautomator.intercept.operation.UiOperationType
import com.kaspersky.components.kautomator.screen.UiScreen

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
 * Delegation class for [androidx.test.uiautomator.UiDevice].
 * Wraps all available public calls and intercepts into [check] and [perform].
 *
 * @see UiDelegate
 * @see UiInterceptor
 */
class UiDeviceInteractionDelegate(
    device: UiDevice
) : UiDelegate<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction> {

    override val interaction = UiDeviceInteraction(device)
    override var interceptor: UiInterceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>? = null

    /**
     * @action must throw exception if something went wrong
     */
    fun check(
        type: UiOperationType,
        description: String? = null,
        assert: UiDevice.() -> Unit
    ) {
        val uiDeviceAssertion = getUiActionBase(type, description, assert)
        check(uiDeviceAssertion)
    }

    /**
     * @action must throw exception if something went wrong
     */
    fun perform(
        type: UiOperationType,
        description: String? = null,
        action: UiDevice.() -> Unit
    ) {
        val uiDeviceAction = getUiActionBase(type, description, action)
        perform(uiDeviceAction)
    }

    private fun getUiActionBase(
        type: UiOperationType,
        description: String? = null,
        action: UiDevice.() -> Unit
    ) = UiOperationBaseImpl(type, description, action)

    fun check(uiAssertion: UiDeviceAssertion) {
        if (!interceptCheck(uiAssertion)) interaction.check(uiAssertion)
    }

    fun perform(uiAction: UiDeviceAction) {
        if (!interceptPerform(uiAction)) interaction.perform(uiAction)
    }

    override fun screenInterceptors(): Iterable<UiInterceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>> =
        UiScreen.UI_DEVICE_INTERCEPTORS

    override fun globalInterceptor(): UiInterceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>? =
        KautomatorConfigurator.uiDeviceInterceptor
}
