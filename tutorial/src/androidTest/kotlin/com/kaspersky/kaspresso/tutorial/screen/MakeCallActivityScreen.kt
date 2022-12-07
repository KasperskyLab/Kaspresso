package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.tutorial.R
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.text.KButton

object MakeCallActivityScreen : KScreen<MakeCallActivityScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val inputNumber = KEditText { withId(R.id.input_number) }
    val makeCallButton = KButton { withId(R.id.make_call_btn) }
}
