package com.kaspersky.kaspresso.kautomatorsample.screen

import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.components.kautomator.screen.UiScreen

object SystemDialogsScreen : UiScreen<SystemDialogsScreen>() {

    override val packageName: String = "com.kaspersky.kaspresso.kautomatorsample"

    val btn1 = UiButton { withId(this@SystemDialogsScreen.packageName, "button_1") }
    val btn2 = UiButton { withId(this@SystemDialogsScreen.packageName, "button_2") }
}
