package com.kaspersky.kaspresso.tutorial.kautomator_screen

import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.components.kautomator.screen.UiScreen

object UiMainScreen : UiScreen<UiMainScreen>() {

    override val packageName: String = "com.kaspersky.kaspresso.tutorial"

    val simpleButton = UiButton { withId(this@UiMainScreen.packageName, "activity_main_simple_sample_button") }
}
