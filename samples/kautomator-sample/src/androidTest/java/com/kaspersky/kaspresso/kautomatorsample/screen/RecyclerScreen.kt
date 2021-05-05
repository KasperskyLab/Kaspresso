package com.kaspersky.kaspresso.kautomatorsample.screen

import com.kaspersky.components.kautomator.component.scroll.UiScrollView
import com.kaspersky.components.kautomator.component.text.UiTextView
import com.kaspersky.components.kautomator.screen.UiScreen

object RecyclerScreen : UiScreen<RecyclerScreen>() {

    override val packageName: String = "com.kaspersky.kaspresso.kautomatorsample"

    val scroll = UiScrollView { withId(this@RecyclerScreen.packageName, "recycler") }

    val toSearch = UiTextView { withText("Scroll to me") }
    val top = UiTextView { withText("Beginning") }
    val bottom = UiTextView { withText("End") }
}
