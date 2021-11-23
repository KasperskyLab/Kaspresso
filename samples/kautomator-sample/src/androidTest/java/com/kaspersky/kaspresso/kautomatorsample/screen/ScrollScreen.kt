package com.kaspersky.kaspresso.kautomatorsample.screen

import com.kaspersky.components.kautomator.component.scroll.UiScrollView
import com.kaspersky.components.kautomator.component.text.UiTextView
import com.kaspersky.components.kautomator.screen.UiScreen

private const val TOP_TEXT = "Beginning"
private const val CENTER_TEXT = "Center"
private const val BOTTOM_TEXT = "End"

class ScrollScreen : UiScreen<ScrollScreen>() {

    override val packageName: String = "com.kaspersky.kaspresso.kautomatorsample"

    val scroll = UiScrollView { withId(this@ScrollScreen.packageName, "scroll") }
    val top = UiTextView { withText(TOP_TEXT) }
    val center = UiTextView { withText(CENTER_TEXT) }
    val bottom = UiTextView { withText(BOTTOM_TEXT) }
}
