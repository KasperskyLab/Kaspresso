package com.kaspersky.kaspressample.screen

import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.kaspersky.kaspressample.R

class MainScreen : Screen<MainScreen>() {

    val nextButton = KButton { withId(R.id.activity_main_button_next) }

    val webViewButton = KButton { withId(R.id.activity_main_button_webview) }

    val recyclerStubButton = KButton { withId(R.id.activity_main_button_recycler_stub) }

    val listStubButton = KButton { withId(R.id.activity_main_button_list_stub) }

    val scrollViewStubButton = KButton { withId(R.id.activity_main_button_scroll_view_stub) }

    val descriptionText = KTextView { withId(R.id.activity_main_text_view_description) }
}