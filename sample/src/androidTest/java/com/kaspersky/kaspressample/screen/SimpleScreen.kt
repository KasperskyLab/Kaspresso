package com.kaspersky.kaspressample.screen

import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.kaspersky.kaspressample.R

object SimpleScreen : Screen<SimpleScreen>() {

    val button1 = KButton { withId(R.id.button_1) }

    val button2 = KButton { withId(R.id.button_2) }

    val edit = KEditText { withId(R.id.edit) }
}