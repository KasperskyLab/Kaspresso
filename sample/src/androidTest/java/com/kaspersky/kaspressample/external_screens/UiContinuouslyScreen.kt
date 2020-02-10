package com.kaspersky.kaspressample.external_screens

import com.kaspersky.components.kautomator.component.text.UiButton

object UiContinuouslyScreen : UiSampleScreen<UiContinuouslyScreen>() {

    val startButton = UiButton {
        withId(this@UiContinuouslyScreen.packageName, "continuously_start_btn")
    }
}