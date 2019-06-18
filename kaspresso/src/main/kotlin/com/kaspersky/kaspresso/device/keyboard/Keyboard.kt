package com.kaspersky.kaspresso.device.keyboard

import android.view.KeyEvent

interface Keyboard {

    /**
     *  Types text char by char.
     *  Use it only when Espresso or UiAutomator are not appropriate.
     *
     *  Required Permissions: INTERNET
     */
    fun typeText(text: String)

    /**
     *  Sends a key event.
     *  Use constants from [KeyEvent] to get the code.
     *
     *  Required Permissions: INTERNET
     *
     *  @param keyEvent the code from a [KeyEvent] constant to send on device.
     */
    fun sendEvent(keyEvent: Int)
}
