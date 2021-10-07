package com.kaspersky.kaspresso.device.keyboard

import android.view.KeyEvent
import androidx.test.espresso.action.ViewActions
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.logger.UiTestLogger

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
        text.map { char -> "input text $char" }
            .forEach { command -> adbServer.performShell(command) }
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
