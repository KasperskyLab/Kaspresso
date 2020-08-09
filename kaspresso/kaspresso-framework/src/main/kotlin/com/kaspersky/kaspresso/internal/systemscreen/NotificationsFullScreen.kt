package com.kaspersky.kaspresso.internal.systemscreen

import com.kaspersky.components.kautomator.component.common.views.UiView
import com.kaspersky.components.kautomator.screen.UiScreen
import java.util.regex.Pattern

object NotificationsFullScreen : UiScreen<NotificationsFullScreen>() {

    override val packageName: String = "com.android.systemui"

    val mobileDataSwitch: UiView = UiView {
        withContentDescription(Pattern.compile(".*Mobile Phone.*"))
    }
}
