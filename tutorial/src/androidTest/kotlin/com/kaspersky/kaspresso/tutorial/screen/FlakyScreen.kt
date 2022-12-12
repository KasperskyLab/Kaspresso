package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.tutorial.R
import io.github.kakaocup.kakao.text.KButton

object FlakyScreen : KScreen<FlakyScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val button1 = KButton { withId(R.id.button_1) }
    val button2 = KButton { withId(R.id.button_2) }
    val button3 = KButton { withId(R.id.button_3) }
    val button4 = KButton { withId(R.id.button_4) }
    val button5 = KButton { withId(R.id.button_5) }
    val button6 = KButton { withId(R.id.button_6) }
}
