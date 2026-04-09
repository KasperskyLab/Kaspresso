@file:Suppress("unused")
package com.kaspersky.components.kautomator.system

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.kaspersky.components.kautomator.common.Environment
import com.kaspersky.components.kautomator.common.KautomatorInUnitTestException
import com.kaspersky.components.kautomator.common.environment
import com.kaspersky.components.kautomator.intercept.base.UiInterceptable
import com.kaspersky.components.kautomator.intercept.delegate.UiDeviceInteractionDelegate
import com.kaspersky.components.kautomator.intercept.interaction.UiDeviceInteraction
import com.kaspersky.components.kautomator.intercept.operation.UiDeviceAction
import com.kaspersky.components.kautomator.intercept.operation.UiDeviceAssertion

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
 * Container class for UiAutomator action and assertions executing in the UiSystem.
 *
 * @see UiSystemActions
 */
object UiSystem : UiSystemActions, UiSystemAssertions,
    UiInterceptable<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction> {

    override val view: UiDeviceInteractionDelegate by lazy(LazyThreadSafetyMode.NONE) {
        if (environment != Environment.AndroidRuntime) throw KautomatorInUnitTestException()

        UiDeviceInteractionDelegate(
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        )
    }

    operator fun invoke(function: UiSystem.() -> Unit) {
        function.invoke(this)
    }
}
