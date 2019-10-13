package com.kaspersky.kaspressample.screen

import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.check_during.CheckDuringSampleActivity

object CheckDuringScreen : KScreen<CheckDuringScreen>() {
    override val layoutId = R.layout.activity_check_during
    override val viewClass = CheckDuringSampleActivity::class.java

    val startButton = KButton {
        withId(R.id.check_during_start_btn)
    }

    val dialogTitle = KTextView {
        withText(R.string.check_during_dialog_title)
    }
}