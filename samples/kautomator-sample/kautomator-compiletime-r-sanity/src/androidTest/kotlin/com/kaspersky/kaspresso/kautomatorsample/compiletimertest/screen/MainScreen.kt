package com.kaspersky.kaspresso.kautomatorsample.compiletimertest.screen

import com.kaspersky.components.kautomator.component.edit.UiEditText
import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.components.kautomator.component.text.UiTextView
import com.kaspersky.components.kautomator.screen.UiScreen
import com.kaspersky.kaspresso.kautomatorsample.compiletimertest.R

object MainScreen : UiScreen<MainScreen>() {
    override val packageName = "com.kaspersky.kaspresso.kautomatorsample.compiletimertest"

    val editText = UiEditText { withId(R.id.editText) }
    val button = UiButton { withId(R.id.button) }
    val textView = UiTextView { withId(R.id.textView) }
}
