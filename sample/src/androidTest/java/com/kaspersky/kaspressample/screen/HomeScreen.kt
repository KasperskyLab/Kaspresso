package com.kaspersky.kaspressample.screen

import com.kaspersky.kaspressample.R
import com.kaspersky.klkakao.screen.Screen
import com.kaspersky.klkakao.text.KButton

class HomeScreen : Screen<HomeScreen>() {

    val title = KButton { withId(R.id.fragment_home_text_view_title) }
}