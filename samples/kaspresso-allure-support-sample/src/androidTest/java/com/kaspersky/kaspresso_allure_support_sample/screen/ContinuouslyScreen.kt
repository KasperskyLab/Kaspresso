package com.kaspersky.kaspresso_allure_support_sample.screen

import com.agoda.kakao.text.KButton
import com.kaspersky.kaspresso_allure_support_sample.R
import com.kaspersky.kaspresso_sample_core.continuously.ContinuouslySampleActivity
import com.kaspersky.kaspresso.screens.KScreen

object ContinuouslyScreen : KScreen<ContinuouslyScreen>() {
    override val layoutId = R.layout.activity_continuously
    override val viewClass = ContinuouslySampleActivity::class.java

    val startButton = KButton {
        withId(R.id.continuously_start_btn)
    }
}
