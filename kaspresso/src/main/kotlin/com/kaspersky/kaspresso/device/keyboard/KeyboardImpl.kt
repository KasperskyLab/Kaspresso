package com.kaspersky.kaspresso.device.keyboard

import android.view.KeyEvent
import com.kaspersky.kaspresso.device.server.AdbServer

class KeyboardImpl : Keyboard {

    /**
     *  Types text char by char in the focused text field.
     *  Use it only when Espresso or UiAutomator are not appropriate (e.g. when you are on the lock screen).
     *
     *  Required Permissions: INTERNET
     */
    override fun typeText(text: String) {
        /*
         * Splits the text into characters and type them one by one
         * to prevent missing characters in the input field caused by very fast typing speed
         */
        text.map { char -> "input text $char" }
            .forEach { command -> AdbServer.performShell(command) }
    }

    /**
     *  Sends a key event.
     *  Use constants from [KeyEvent] to get the code.
     *
     *  Required Permissions: INTERNET
     *
     *  @param keyEvent the code from a [KeyEvent] constant to send on device.
     */
    override fun sendEvent(keyEvent: Int) {
        AdbServer.performShell("input keyevent $keyEvent")
    }
}
