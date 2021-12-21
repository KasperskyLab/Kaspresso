package com.kaspersky.kaspresso.kautomatorsample.screen

import com.kaspersky.components.kautomator.component.scroll.UiHorizontalScrollView
import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.components.kautomator.screen.UiScreen

object NestedScrollViewsScreen: UiScreen<NestedScrollViewsScreen>() {

    override val packageName: String = "com.kaspersky.kaspresso.kautomatorsample"

    val hscrollView = UiHorizontalScrollView { withId(this@NestedScrollViewsScreen.packageName,"hscrollView")}
    val nnHscrollView = UiHorizontalScrollView { withId(this@NestedScrollViewsScreen.packageName,"nnHscrollView")}
    val hbutton1 = UiButton { withId(this@NestedScrollViewsScreen.packageName,"hvText1") }
    val hbutton7 = UiButton { withId(this@NestedScrollViewsScreen.packageName, "hvText7") }
    val button1 = UiButton { withId(this@NestedScrollViewsScreen.packageName, "tvText1") }
    val button20 = UiButton { withId(this@NestedScrollViewsScreen.packageName, "tvText20") }
    val nnHbutton5 = UiButton { withId(this@NestedScrollViewsScreen.packageName, "nnHtvText5") }
}
