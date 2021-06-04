package com.kaspersky.kaspresso.tutorial.screen

import com.agoda.kakao.text.KButton
import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.tutorial.MainActivity
import com.kaspersky.kaspresso.tutorial.R

object MainScreen : KScreen<MainScreen>() {

    override val layoutId: Int = R.layout.activity_main
    override val viewClass: Class<*> = MainActivity::class.java

    val simpleButton = KButton { withId(R.id.activity_main_simple_sample_button) }
}
