package com.kaspersky.kaspressample.external_screens

import com.kaspersky.components.kautomator.dsl.text.UiButton

object UiMainScreen : UiSampleScreen<UiMainScreen>() {

    val simpleButton = UiButton { withId(this@UiMainScreen.packageName, "activity_main_simple_sample_button") }

    val flakyButton = UiButton { withId(this@UiMainScreen.packageName, "activity_main_flaky_sample_button") }

    val continuouslyButton = UiButton { withId(this@UiMainScreen.packageName, "activity_main_continuously_sample_button") }

    val composeButton = UiButton { withId(this@UiMainScreen.packageName, "activity_main_complex_compose_sample_button") }

    val idleWaitingButton = UiButton { withId(this@UiMainScreen.packageName, "activity_main_idlewaiting_sample_button") }
}