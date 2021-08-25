package com.kaspersky.kaspresso.device

import androidx.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.failure.exceptions.KautomatorOnSharedTestException

class JvmUiDeviceConfig : UiDeviceConfig {
    override fun uiDevice(): UiDevice = throw KautomatorOnSharedTestException()
}
