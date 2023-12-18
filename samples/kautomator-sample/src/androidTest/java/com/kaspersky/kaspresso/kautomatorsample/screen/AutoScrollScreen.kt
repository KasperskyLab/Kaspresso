package com.kaspersky.kaspresso.kautomatorsample.screen

import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.components.kautomator.screen.UiScreen

object AutoScrollScreen : UiScreen<AutoScrollScreen>() {

    override val packageName: String = "com.kaspersky.kaspresso.kautomatorsample"

    val button1 = UiButton { withId(this@AutoScrollScreen.packageName, "tvText1") }
    val button20 = UiButton { withId(this@AutoScrollScreen.packageName, "tvText20") }
}
