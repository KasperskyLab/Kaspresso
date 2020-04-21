package com.kaspersky.kaspressample.external_screens

import com.kaspersky.components.kautomator.component.scroll.UiScrollView
import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.flaky.CommonFlakyActivity

object UiCommonFlakyScreen : UiSampleScreen<UiCommonFlakyScreen>() {

    override val layoutId: Int? = R.layout.activity_common_flaky
    override val viewClass: Class<*>? = CommonFlakyActivity::class.java

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