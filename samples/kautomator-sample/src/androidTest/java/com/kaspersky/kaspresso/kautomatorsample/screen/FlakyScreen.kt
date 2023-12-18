package com.kaspersky.kaspresso.kautomatorsample.screen

import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.components.kautomator.screen.UiScreen

object FlakyScreen : UiScreen<FlakyScreen>() {

    private const val TOP_TEXT = "Beginning"
    private const val CENTER_TEXT = "Center"
    private const val BOTTOM_TEXT = "End"

    override val packageName: String = "com.kaspersky.kaspresso.kautomatorsample"

    val btn1 = UiButton { withId(this@FlakyScreen.packageName, "flaky_button_1") }
}
