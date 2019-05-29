package com.kaspersky.kaspressample.screen

import com.kaspersky.kaspressample.R
import com.kaspersky.klkakao.screen.Screen
import com.kaspersky.klkakao.text.KButton
import com.kaspersky.klkakao.text.KTextView


class MainScreen : Screen<MainScreen>() {

    val nextButton = KButton { withId(R.id.activity_main_button_next) }

    val descriptionText = KTextView { withId(R.id.activity_main_text_view_description) }
}