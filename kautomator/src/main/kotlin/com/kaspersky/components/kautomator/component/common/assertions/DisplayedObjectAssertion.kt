package com.kaspersky.components.kautomator.component.common.assertions

import androidx.test.uiautomator.UiObject2
import com.google.common.truth.Truth.assertThat
import com.kaspersky.components.kautomator.intercept.operation.UiOperation
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
 * Special separate Assertion to determine UiAutomator View is displayed or not
 */
class DisplayedObjectAssertion private constructor(
    override val type: UiDisplayedAssertionType
) : UiOperation<UiObject2> {

    companion object {
        fun assertDisplayed() = DisplayedObjectAssertion(UiDisplayedAssertionType.IS_DISPLAYED)

        @Deprecated("It does not work for now. This assertion will throw UnfoundedObjectException")
        fun assertNotDisplayed() = DisplayedObjectAssertion(UiDisplayedAssertionType.IS_NOT_DISPLAYED)
    }

    override val description: String? = null

    override fun execute(innerView: UiObject2) {
        when (type) {
            UiDisplayedAssertionType.IS_DISPLAYED -> assertThat(innerView).isNotNull()
            /**
             * This check will never fail because [innerView] is not nullable
             */
            UiDisplayedAssertionType.IS_NOT_DISPLAYED -> assertThat(innerView).isNull()
        }
    }

    override fun toString(): String {
        return "DisplayedObjectAssertion(type=$type, description=$description)"
    }

    enum class UiDisplayedAssertionType : UiOperationType {
        IS_DISPLAYED,
        IS_NOT_DISPLAYED,
    }
}
