package com.kaspersky.kaspressample.screen

import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.continuously.ContinuouslySampleActivity

object ContinuouslyScreen : KScreen<ContinuouslyScreen>() {
    override val layoutId = R.layout.activity_continuously
    override val viewClass = ContinuouslySampleActivity::class.java

    val startButton = KButton {
        withId(R.id.coninuously_start_btn)
    }

    val dialogTitle = KTextView {
        withText(R.string.continuously_dialog_title)
    }
}