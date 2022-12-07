package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.components.kautomator.component.common.views.UiView
import com.kaspersky.components.kautomator.component.text.UiTextView
import com.kaspersky.components.kautomator.screen.UiScreen

object PhoneCallScreen : UiScreen<PhoneCallScreen>() {

    override val packageName: String = "com.android.dialer"

    val contactName = UiTextView {
        withId(
            packageName = this@PhoneCallScreen.packageName,
            resourceId = "contactgrid_contact_name"
        )
    }

    val endCallButton = UiView {
        withId(
            packageName = this@PhoneCallScreen.packageName,
            resourceId = "incall_end_call"
        )
    }
}
