package com.kaspersky.kaspressample.screen

import com.kaspersky.kaspressample.R
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KButton

object SystemDialogsScreen : KScreen<SystemDialogsScreen>() {

    override val layoutId: Int = R.layout.activity_system_dialogs
    override val viewClass: Class<*> = SystemDialogsScreen::class.java

    val btn1 = KButton { withId(R.id.button_1) }
    val btn2 = KButton { withId(R.id.button_2) }
}
