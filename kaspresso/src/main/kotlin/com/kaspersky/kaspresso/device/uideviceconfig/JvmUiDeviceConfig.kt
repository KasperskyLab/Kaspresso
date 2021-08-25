package com.kaspersky.kaspresso.device.uideviceconfig

import androidx.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.failure.exceptions.KautomatorInSharedTestException

class JvmUiDeviceConfig : UiDeviceConfig {
    override fun uiDevice(): UiDevice = throw KautomatorInSharedTestException()
}
