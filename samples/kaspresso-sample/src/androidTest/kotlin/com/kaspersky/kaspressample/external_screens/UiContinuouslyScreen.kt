package com.kaspersky.kaspressample.external_screens

import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.continuously.ContinuouslySampleActivity

object UiContinuouslyScreen : UiSampleScreen<UiContinuouslyScreen>() {

    override val layoutId: Int? = R.layout.activity_continuously
    override val viewClass: Class<*>? = ContinuouslySampleActivity::class.java

    val startButton = UiButton {
        withId(this@UiContinuouslyScreen.packageName, "continuously_start_btn")
    }
}
