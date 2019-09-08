package com.kaspersky.kaspressample.screen

import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.R

object MainScreen : KScreen<MainScreen>() {

    override val layoutId: Int? = R.layout.activity_main
    override val viewClass: Class<*>? = MainActivity::class.java

    val nextButton = KButton { withId(R.id.activity_main_button_next) }

    val webViewButton = KButton { withId(R.id.activity_main_button_webview) }

    val recyclerStubButton = KButton { withId(R.id.activity_main_button_recycler_stub) }

    val listStubButton = KButton { withId(R.id.activity_main_button_list_stub) }

    val scrollViewStubButton = KButton { withId(R.id.activity_main_button_scroll_view_stub) }

    val descriptionText = KTextView { withId(R.id.activity_main_title) }
}