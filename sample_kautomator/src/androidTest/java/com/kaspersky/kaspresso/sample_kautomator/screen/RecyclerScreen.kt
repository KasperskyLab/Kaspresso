package com.kaspersky.kaspresso.sample_kautomator.screen

import com.kaspersky.components.kautomator.dsl.screen.UiScreen
import com.kaspersky.components.kautomator.dsl.scroll.UiScrollView
import com.kaspersky.components.kautomator.dsl.text.UiTextView

object RecyclerScreen : UiScreen<RecyclerScreen>() {

    private const val MAIN_APP_PACKAGE_ID = "com.kaspersky.kaspresso.sample_kautomator"

    val scroll = UiScrollView { withId(this@RecyclerScreen.MAIN_APP_PACKAGE_ID, "recycler") }

    val toSearch = UiTextView { withText("Scroll to me") }
    val top = UiTextView { withText("Beginning") }
    val bottom = UiTextView { withText("End") }
}