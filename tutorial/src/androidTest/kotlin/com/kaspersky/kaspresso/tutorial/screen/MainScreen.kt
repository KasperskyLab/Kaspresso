package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.tutorial.R
import io.github.kakaocup.kakao.text.KButton

object MainScreen : KScreen<MainScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val simpleActivityButton = KButton { withId(R.id.simple_activity_btn) }
    val wifiActivityButton = KButton { withId(R.id.wifi_activity_btn) }
    val loginActivityButton = KButton { withId(R.id.login_activity_btn) }
    val notificationActivityButton = KButton { withId(R.id.notification_activity_btn) }
    val makeCallActivityButton = KButton { withId(R.id.make_call_activity_btn) }
    val flakyActivityButton = KButton { withId(R.id.flaky_activity_btn) }
}
