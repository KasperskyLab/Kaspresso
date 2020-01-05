package com.kaspersky.kaspressample.external_screens

import com.kaspersky.components.uiautomator_dsl.dsl.swipe.UiSwipeView
import com.kaspersky.components.uiautomator_dsl.dsl.text.UiButton

object UiCommonFlakyScreen : UiSampleScreen<UiCommonFlakyScreen>() {

    // todo create UiScrollView
    val scrollView = UiSwipeView {
        withId(PACKAGE, "scroll_view")
    }

    val btn1 = UiButton {
        withId(PACKAGE, "scroll_view_btn1")
    }

    val btn3 = UiButton {
        withId(PACKAGE, "scroll_view_btn3")
    }

    val btn5 = UiButton {
        withId(PACKAGE, "scroll_view_btn5")
    }

    val tv6 = UiButton {
        withId(PACKAGE, "scroll_view_tv6")
    }
}