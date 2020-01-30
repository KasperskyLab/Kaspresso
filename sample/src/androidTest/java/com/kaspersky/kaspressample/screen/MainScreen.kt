package com.kaspersky.kaspressample.screen

import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspresso.screens.KScreen

object MainScreen : KScreen<MainScreen>() {

    override val layoutId: Int? = R.layout.activity_main
    override val viewClass: Class<*>? = MainActivity::class.java

    val simpleButton = KButton { withId(R.id.activity_main_button_simple) }

    val webViewButton = KButton { withId(R.id.activity_main_button_webview) }

    val scrollViewStubButton = KButton { withId(R.id.activity_main_button_scroll_view_sample) }

    val continuouslyButton = KButton { withId(R.id.activity_main_button_continuously_sample) }

    val composeButton = KButton { withId(R.id.activity_main_button_complex_compose_sample) }

    val idleWaitingButton = KButton { withId(R.id.activity_main_button_idlewaiting_sample) }

    val descriptionText = KTextView { withId(R.id.activity_main_title) }
}