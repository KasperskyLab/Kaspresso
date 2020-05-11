package com.kaspersky.kaspresso.internal.outscreens

import com.kaspersky.components.kautomator.component.switch.UiSwitch
import com.kaspersky.components.kautomator.screen.UiScreen

internal object NotificationScreen : UiScreen<NotificationScreen>() {

    override val packageName: String = "com.android.systemui"

    val wifiSwitcher = UiSwitch {
        withContentDescription("Wi-Fi,Wifi signal full.,AndroidWifi")
    }
}