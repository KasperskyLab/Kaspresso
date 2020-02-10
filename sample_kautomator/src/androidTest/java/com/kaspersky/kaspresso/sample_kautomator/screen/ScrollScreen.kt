package com.kaspersky.kaspresso.sample_kautomator.screen

import com.kaspersky.components.kautomator.screen.UiScreen
import com.kaspersky.components.kautomator.component.scroll.UiScrollView
import com.kaspersky.components.kautomator.component.text.UiTextView
import com.kaspersky.kaspresso.sample_kautomator.R
import com.kaspersky.kaspresso.sample_kautomator.scroll.ScrollActivity

object ScrollScreen : UiScreen<ScrollScreen>() {

    private const val TOP_TEXT = "Beginning"
    private const val CENTER_TEXT = "Center"
    private const val BOTTOM_TEXT = "End"

    override val packageName: String = "com.kaspersky.kaspresso.sample_kautomator"
    override val layoutId: Int? = R.layout.activity_scroll
    override val viewClass: Class<*>? = ScrollActivity::class.java

    val scroll = UiScrollView { withId(this@ScrollScreen.packageName, "scroll") }
    val top = UiTextView { withText(this@ScrollScreen.TOP_TEXT) }
    val center = UiTextView { withText(this@ScrollScreen.CENTER_TEXT) }
    val bottom = UiTextView { withText(this@ScrollScreen.BOTTOM_TEXT) }
}