package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.tutorial.R
import com.kaspersky.kaspresso.tutorial.wifi.WiFiActivity
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.junit.Test

object WiFiScreen: KScreen<WiFiScreen>() {
    override val layoutId: Int = R.layout.activity_wifi
    override val viewClass: Class<*> = WiFiActivity::class.java

    val wifiStatusTitle = KTextView { withId(R.id.wifi_status) }
    val wifiStatusButton = KButton { withId(R.id.check_wifi_btn) }
}
