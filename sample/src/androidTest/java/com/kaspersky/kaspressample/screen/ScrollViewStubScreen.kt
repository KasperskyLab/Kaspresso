package com.kaspersky.kaspressample.screen

import com.agoda.kakao.screen.Screen
import com.agoda.kakao.scroll.KScrollView
import com.agoda.kakao.text.KButton
import com.kaspersky.kaspressample.R

class ScrollViewStubScreen : Screen<ScrollViewStubScreen>() {

    val scrollViewStub = KScrollView {
        withId(R.id.scroll_view)
    }

    val btn3 = KButton {
        withId(R.id.scroll_view_stub_btn3)
    }

    val btn5 = KButton {
        withId(R.id.scroll_view_stub_btn5)
    }
}