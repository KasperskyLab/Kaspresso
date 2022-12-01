package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.tutorial.R
import io.github.kakaocup.kakao.text.KButton

object NotificationActivityScreen : KScreen<NotificationActivityScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val showNotificationButton = KButton { withId(R.id.show_notification_button) }
}
