package com.kaspersky.kaspressample.screen

import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.scrollresolver.ScrollResolverActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KButton

object KScrollViewWithPaddingScreen : KScreen<KScrollViewWithPaddingScreen>() {

    override val layoutId: Int? = R.layout.activity_scrollview_with_padding
    override val viewClass: Class<*>? = ScrollResolverActivity::class.java

    val hbutton1 = KButton { withId(R.id.hvText1) }
    val hbutton7 = KButton { withId(R.id.hvText7) }
    val button1 = KButton { withId(R.id.tvText1) }
    val button18 = KButton { withId(R.id.tvText18) }
    val button20 = KButton { withId(R.id.tvText20) }
}
