package com.kaspersky.kaspressample.screen

import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.text.KButton
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.SimpleFragment

object SimpleScreen : KScreen<SimpleScreen>() {

    override val layoutId: Int? = R.layout.fragment_simple
    override val viewClass: Class<*>? = SimpleFragment::class.java

    val button1 = KButton { withId(R.id.button_1) }

    val button2 = KButton { withId(R.id.button_2) }

    val edit = KEditText { withId(R.id.edit) }
}