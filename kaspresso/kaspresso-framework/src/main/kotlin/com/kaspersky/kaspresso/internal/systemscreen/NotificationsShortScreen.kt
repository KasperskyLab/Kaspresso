package com.kaspersky.kaspresso.internal.systemscreen

import com.kaspersky.components.kautomator.component.common.views.UiView
import com.kaspersky.components.kautomator.screen.UiScreen

object NotificationsShortScreen : UiScreen<NotificationsShortScreen>() {

    override val packageName: String = "com.android.systemui"

    val mainNotification: UiView = UiView {
        withId(this@NotificationsShortScreen.packageName, "header")
    }
}
