package com.kaspersky.kaspresso.alluresupport.sample.screen

import com.kaspersky.kaspresso.R
import com.kaspersky.kaspresso_sample_core.continuously.ContinuouslySampleActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KButton

object ContinuouslyScreen : KScreen<ContinuouslyScreen>() {
    override val layoutId = R.layout.activity_continuously
    override val viewClass = ContinuouslySampleActivity::class.java

    val startButton = KButton {
        withId(R.id.continuously_start_btn)
    }
}
