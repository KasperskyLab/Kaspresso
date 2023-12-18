package com.kaspersky.kaspressample.screen

import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.flaky.CommonFlakyActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.scroll.KScrollView
import io.github.kakaocup.kakao.text.KButton

object CommonFlakyScreen : KScreen<CommonFlakyScreen>() {

    override val layoutId: Int = R.layout.activity_common_flaky
    override val viewClass: Class<*> = CommonFlakyActivity::class.java

    val scrollView = KScrollView { withId(R.id.scroll_view) }

    val btn1 = KButton { withId(R.id.scroll_view_btn1) }
    val btn3 = KButton { withId(R.id.scroll_view_btn3) }
    val btn5 = KButton { withId(R.id.scroll_view_btn5) }

    val tv6 = KButton { withId(R.id.scroll_view_tv6) }
}
