package com.kaspersky.components.uiautomator_dsl.intercepting.interaction

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2

class UiInteraction(
    val selector: BySelector
) {

    var uiObject2: UiObject2? = null
        private set

    fun tryToFindUiObject() {
        uiObject2 = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).findObject(selector)
    }
}