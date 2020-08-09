package com.kaspersky.kaspresso.internal.systemscreen

import android.widget.Switch
import com.kaspersky.components.kautomator.component.switch.UiSwitch
import com.kaspersky.components.kautomator.screen.UiScreen

object NotificationsMobileDataScreen : UiScreen<NotificationsMobileDataScreen>() {

    override val packageName: String = "com.android.systemui"

    val mobileDataSwitch: UiSwitch = UiSwitch {
        withClassName(Switch::class.java)
    }
}
