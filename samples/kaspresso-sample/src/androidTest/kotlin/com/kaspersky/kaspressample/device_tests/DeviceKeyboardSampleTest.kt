package com.kaspersky.kaspressample.device_tests

import android.Manifest
import android.view.KeyEvent
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.device.DeviceSampleActivity
import com.kaspersky.kaspressample.screen.DeviceSampleScreen
import com.kaspersky.kaspresso.device.keyboard.Keyboard
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

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

class DeviceKeyboardSampleTest : TestCase() {

    companion object {
        const val TEXT_TO_BE_TYPED = "Kaspresso"
    }

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = activityScenarioRule<DeviceSampleActivity>()

    @Test
    fun keyboardSampleTest() {
        run {

            /**
             * Consider to use [ViewActions.typeText] or [UiObject.setText] instead of [Keyboard.typeText]
             */
            step("Type text using [Keyboard.typeText]") {
                DeviceSampleScreen {
                    input {
                        device.keyboard.typeText(TEXT_TO_BE_TYPED)
                        hasText(TEXT_TO_BE_TYPED)
                    }
                }
            }

            /**
             * Consider to use [ViewActions.pressKey] or [UiDevice.pressKeyCode] instead of [Keyboard.sendEvent]
             */
            step("Type text using [Keyboard.sendEvent]") {
                DeviceSampleScreen {
                    input {
                        clearText()
                    }
                }

                // Type `kaspresso`
                device.keyboard.sendEvent(KeyEvent.KEYCODE_K)
                device.keyboard.sendEvent(KeyEvent.KEYCODE_A)
                device.keyboard.sendEvent(KeyEvent.KEYCODE_S)
                device.keyboard.sendEvent(KeyEvent.KEYCODE_P)
                device.keyboard.sendEvent(KeyEvent.KEYCODE_R)
                device.keyboard.sendEvent(KeyEvent.KEYCODE_E)
                device.keyboard.sendEvent(KeyEvent.KEYCODE_S)
                device.keyboard.sendEvent(KeyEvent.KEYCODE_S)
                device.keyboard.sendEvent(KeyEvent.KEYCODE_O)

                DeviceSampleScreen {
                    input {
                        hasText("kaspresso")
                    }
                }
            }
        }
    }
}
