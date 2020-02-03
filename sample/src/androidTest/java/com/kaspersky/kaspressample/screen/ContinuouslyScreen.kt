package com.kaspersky.kaspressample.screen

import com.agoda.kakao.text.KButton
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.continuously.ContinuouslySampleActivity
import com.kaspersky.kaspresso.screens.KScreen

object ContinuouslyScreen : KScreen<ContinuouslyScreen>() {
    override val layoutId = R.layout.activity_continuously
    override val viewClass = ContinuouslySampleActivity::class.java

    val startButton = KButton {
        withId(R.id.continuously_start_btn)
    }
}