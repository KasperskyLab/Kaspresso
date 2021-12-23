package com.kaspersky.kaspressample.screen

import com.kaspersky.components.kautomator.component.scroll.UiHorizontalScrollView
import com.kaspersky.components.kautomator.component.scroll.UiScrollView
import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.components.kautomator.screen.UiScreen

object UiScrollViewWithPaddingScreen : UiScreen<UiScrollViewWithPaddingScreen>() {

    override val packageName: String = "com.kaspersky.kaspressample"

    val hScrollView = UiHorizontalScrollView { withId(this@UiScrollViewWithPaddingScreen.packageName, "hscroll_view") }

    val scrollView = UiScrollView { withId(this@UiScrollViewWithPaddingScreen.packageName, "scroll_view") }

    val hbutton1 = UiButton { withId(this@UiScrollViewWithPaddingScreen.packageName, "hvText1") }
    val hbutton7 = UiButton { withId(this@UiScrollViewWithPaddingScreen.packageName, "hvText7") }
    val button1 = UiButton { withId(this@UiScrollViewWithPaddingScreen.packageName, "tvText1") }
    val button20 = UiButton { withId(this@UiScrollViewWithPaddingScreen.packageName, "tvText20") }
}
