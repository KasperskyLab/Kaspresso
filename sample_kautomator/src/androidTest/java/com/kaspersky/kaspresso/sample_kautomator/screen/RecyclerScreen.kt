package com.kaspersky.kaspresso.sample_kautomator.screen

import com.kaspersky.components.kautomator.screen.UiScreen
import com.kaspersky.components.kautomator.component.scroll.UiScrollView
import com.kaspersky.components.kautomator.component.text.UiTextView
import com.kaspersky.kaspresso.sample_kautomator.R
import com.kaspersky.kaspresso.sample_kautomator.recycler.RecyclerActivity

object RecyclerScreen : UiScreen<RecyclerScreen>() {

    override val packageName: String = "com.kaspersky.kaspresso.sample_kautomator"
    override val layoutId: Int? = R.layout.activity_recycler
    override val viewClass: Class<*>? = RecyclerActivity::class.java

    val scroll = UiScrollView { withId(this@RecyclerScreen.packageName, "recycler") }

    val toSearch = UiTextView { withText("Scroll to me") }
    val top = UiTextView { withText("Beginning") }
    val bottom = UiTextView { withText("End") }
}