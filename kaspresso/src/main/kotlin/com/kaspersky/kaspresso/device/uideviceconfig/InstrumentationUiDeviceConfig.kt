package com.kaspersky.kaspresso.device.uideviceconfig

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice

class InstrumentationUiDeviceConfig : UiDeviceConfig {
    override fun uiDevice(): UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
}
