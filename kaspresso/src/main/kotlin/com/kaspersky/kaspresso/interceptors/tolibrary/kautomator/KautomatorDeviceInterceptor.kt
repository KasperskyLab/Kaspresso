package com.kaspersky.kaspresso.interceptors.tolibrary.kautomator

import com.kaspersky.components.kautomator.intercept.interaction.UiDeviceInteraction
import com.kaspersky.components.kautomator.intercept.operation.UiDeviceAction
import com.kaspersky.components.kautomator.intercept.operation.UiDeviceAssertion
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.DeviceBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.tolibrary.LibraryInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.kautomator.DeviceWatcherInterceptor

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
 * Kaspresso's implementation of Kautomator's UiDeviceInteraction interceptor.
 */
internal class KautomatorDeviceInterceptor(
    private val deviceBehaviorInterceptors: List<DeviceBehaviorInterceptor>,
    private val deviceWatcherInterceptors: List<DeviceWatcherInterceptor>
) : LibraryInterceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction> {

    /**
     * Folds all [DeviceBehaviorInterceptor]'s one into another in the order from the first to the last with the actual
     * [UiDeviceInteraction.check] call as the initial, and invokes the resulting lambda.
     * Also, adds a call of all [DeviceWatcherInterceptor] in the initial lambda
     */
    override fun interceptCheck(interaction: UiDeviceInteraction, assertion: UiDeviceAssertion) {
        deviceBehaviorInterceptors.fold(
            initial = {
                deviceWatcherInterceptors.forEach {
                    it.interceptCheck(interaction, assertion)
                }
                interaction.check(assertion)
            },
            operation = {
                    acc, deviceBehaviorInterceptor ->
                { deviceBehaviorInterceptor.interceptCheck(interaction, assertion, acc) }
            }
        ).invoke()
    }

    /**
     * Folds all [DeviceBehaviorInterceptor]'s one into another in the order from the first to the last with the actual
     * [UiDeviceInteraction.perform] call as the initial, and invokes the resulting lambda.
     * Also, adds a call of [DeviceWatcherInterceptor] in the initial lambda
     */
    override fun interceptPerform(interaction: UiDeviceInteraction, action: UiDeviceAction) {
        deviceBehaviorInterceptors.fold(
            initial = {
                deviceWatcherInterceptors.forEach {
                    it.interceptPerform(interaction, action)
                }
                interaction.perform(action)
            },
            operation = {
                    acc, deviceBehaviorInterceptor ->
                { deviceBehaviorInterceptor.interceptPerform(interaction, action, acc) }
            }
        ).invoke()
    }
}
