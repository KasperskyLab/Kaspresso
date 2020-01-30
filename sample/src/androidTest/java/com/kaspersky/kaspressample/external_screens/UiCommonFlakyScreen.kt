package com.kaspersky.kaspressample.external_screens

import com.kaspersky.components.kautomator.dsl.scroll.UiScrollView
import com.kaspersky.components.kautomator.dsl.text.UiButton

object UiCommonFlakyScreen : UiSampleScreen<UiCommonFlakyScreen>() {

    val scrollView = UiScrollView {
        withId(this@UiCommonFlakyScreen.packageName, "scroll_view")
    }

    val btn1 = UiButton {
        withId(this@UiCommonFlakyScreen.packageName, "scroll_view_btn1")
    }

    val btn3 = UiButton {
        withId(this@UiCommonFlakyScreen.packageName, "scroll_view_btn3")
    }

    val btn5 = UiButton {
        withId(this@UiCommonFlakyScreen.packageName, "scroll_view_btn5")
    }

    val tv6 = UiButton {
        withId(this@UiCommonFlakyScreen.packageName, "scroll_view_tv6")
    }
}