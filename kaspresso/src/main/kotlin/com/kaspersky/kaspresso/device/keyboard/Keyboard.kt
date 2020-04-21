package com.kaspersky.kaspresso.device.keyboard

import android.view.KeyEvent
import androidx.test.espresso.action.ViewActions
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import com.kaspersky.kaspresso.annotations.RequiresAdbServer

/**
 * Use this API only if neither Espresso, nor UiAutomator
 * work for some reasons. E.g. because of animations.
 *
 * Using this API is highly discouraged. Consider to use the built-in API
 * whenever it's possible as it described in the documentation for methods.
 *
 * Required: Started AdbServer
 *     1. Download a file "kaspresso/artifacts/desktop.jar"
 *     2. Start AdbServer => input in cmd "java jar path_to_file/desktop.jar"
 * Methods demanding to use AdbServer in the default implementation of this interface are marked.
 *     But nobody can't deprecate you to write implementation that doesn't require AdbServer.
 */
interface Keyboard {

    /**
     * Types text char by char in the focused text field.
     * Use it only when Espresso or UiAutomator are not appropriate (e.g. when you are on the lock screen).
     *
     * Consider to use [ViewActions.typeText].
     * Also, consider to use [UiObject.setText].
     *
     * Required Permissions: INTERNET
     */
    @RequiresAdbServer
    fun typeText(text: String)

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
    @RequiresAdbServer
    fun sendEvent(keyEvent: Int)
}
