package com.kaspersky.kaspresso.device.keyboard

import android.view.KeyEvent
import androidx.test.espresso.action.ViewActions
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.logger.UiTestLogger

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
 * The implementation of the [Keyboard] interface.
 */
class KeyboardImpl(
    private val logger: UiTestLogger,
    private val adbServer: AdbServer
) : Keyboard {

    /**
     * Types text char by char in the focused text field.
     * Use it only when Espresso or UiAutomator are not appropriate (e.g. when you are on the lock screen).
     *
     * Consider to use [ViewActions.typeText].
     * Also, consider to use [UiObject.setText].
     *
     * Required Permissions: INTERNET
     */
    override fun typeText(text: String) {
        logger.i("Type text $text")
        /*
         * Splits the text into characters and type them one by one
         * to prevent missing characters in the input field caused by very fast typing speed
         */
        text.forEach { char -> adbServer.performShell("input text $char") }
    }

    /**
     * Sends a key event.
     * Use constants from [KeyEvent] to get the code.
     *
     * Consider to use [ViewActions.pressKey].
     * Also, consider to use [UiDevice.pressKeyCode],
     * or more semantic methods like [UiDevice.pressMenu], [UiDevice.pressDPadLeft] and so on.
     *
     * Required Permissions: INTERNET
     *
     * @param keyEvent the code from a [KeyEvent] constant to send on device.
     */
    override fun sendEvent(keyEvent: Int) {
        logger.i("Send key event $keyEvent")
        adbServer.performShell("input keyevent $keyEvent")
    }
}
