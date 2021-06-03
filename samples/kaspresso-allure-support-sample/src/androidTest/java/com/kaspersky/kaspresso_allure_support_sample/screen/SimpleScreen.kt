package com.kaspersky.kaspresso_allure_support_sample.screen

import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.text.KButton
import com.kaspersky.kaspresso_allure_support_sample.R
import com.kaspersky.kaspresso_sample_core.simple.SimpleActivity
import com.kaspersky.kaspresso.screens.KScreen

object SimpleScreen : KScreen<SimpleScreen>() {

    override val layoutId: Int? = R.layout.activity_simple
    override val viewClass: Class<*>? = SimpleActivity::class.java

    val button1 = KButton { withId(R.id.button_1) }

    val button2 = KButton { withId(R.id.button_2) }

    val edit = KEditText { withId(R.id.edit) }
}
