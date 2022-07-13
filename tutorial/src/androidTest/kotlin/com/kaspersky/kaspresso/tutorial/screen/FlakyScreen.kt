package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.kaspresso.tutorial.R
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.scroll.KScrollView
import io.github.kakaocup.kakao.text.KButton

object FlakyScreen : Screen<FlakyScreen>() {

    val scrollView = KScrollView { withId(R.id.scroll_view_flaky) }

    val flakyBtn = KButton { withId(R.id.sixth_element_btn) }

    val flakyTextView = KButton { withId(R.id.second_element_tv) }
}
