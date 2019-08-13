package com.kaspersky.kaspressample.screen

import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.kaspersky.kaspressample.R

object MainScreen : Screen<MainScreen>() {

    val nextButton = KButton { withId(R.id.activity_main_button_next) }

    val webViewButton = KButton { withId(R.id.activity_main_button_webview) }

    val descriptionText = KTextView { withId(R.id.activity_main_title) }
}