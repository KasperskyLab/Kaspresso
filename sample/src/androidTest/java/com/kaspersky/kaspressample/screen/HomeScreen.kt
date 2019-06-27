package com.kaspersky.kaspressample.screen

import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.kaspersky.kaspressample.R

class HomeScreen : Screen<HomeScreen>() {

    val title = KButton { withId(R.id.fragment_home_text_view_title) }
}