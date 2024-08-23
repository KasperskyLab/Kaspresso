package com.kaspersky.kaspresso.tutoreal.screen

import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.tutorial.R
import io.github.kakaocup.kakao.text.KButton

object MainSceen: KScreen<MainSceen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val simpleActivityButton = KButton {withId(R.id.simple_activity_btn)}
}
