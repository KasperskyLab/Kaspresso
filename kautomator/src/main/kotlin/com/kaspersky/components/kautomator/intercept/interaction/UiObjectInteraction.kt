package com.kaspersky.components.kautomator.intercept.interaction

import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import com.kaspersky.components.kautomator.component.common.builders.UiViewSelector
import com.kaspersky.components.kautomator.intercept.exception.UnfoundedUiObjectException
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAssertion

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
 * Provides an interaction to work with the UiView described by [selector]
 */
class UiObjectInteraction(
    val device: UiDevice,
    val selector: UiViewSelector,
    val description: String
) : UiInteraction<UiObjectAssertion, UiObjectAction> {

    var uiObject2: UiObject2? = calculateUiObject()
        private set

    private fun calculateUiObject(): UiObject2? {
        return selector.selectors.firstNotNullOfOrNull { getUiObject2OrNull(it) }
    }

    private fun getUiObject2OrNull(bySelector: BySelector): UiObject2? {
        val uiObjects = device.findObjects(bySelector)
        if (uiObjects.isNotEmpty() && selector.index < uiObjects.size) {
            return uiObjects[selector.index]
        }
        return null
    }

    override fun check(assertion: UiObjectAssertion) {
        assertion.execute(uiObject2 ?: throw UnfoundedUiObjectException(selector))
    }

    override fun perform(action: UiObjectAction) {
        action.execute(uiObject2 ?: throw UnfoundedUiObjectException(selector))
    }

    fun reFindUiObject() {
        uiObject2 = calculateUiObject()
    }

    override fun toString(): String {
        return "UiObjectInteraction(device=$device, selector=$selector, description='$description', uiObject2=$uiObject2)"
    }
}
