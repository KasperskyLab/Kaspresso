package com.kaspersky.kaspresso_allure_support_sample.screen

import com.kaspersky.kaspresso_sample_core.MainActivity
import com.kaspersky.kaspresso_allure_support_sample.R
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

object MainScreen : KScreen<MainScreen>() {

    override val layoutId: Int? = R.layout.activity_main
    override val viewClass: Class<*>? = MainActivity::class.java

    val simpleButton = KButton { withId(R.id.activity_main_simple_sample_button) }

    val webViewButton = KButton { withId(R.id.activity_main_webview_sample_button) }

    val flakyButton = KButton { withId(R.id.activity_main_flaky_sample_button) }

    val continuouslyButton = KButton { withId(R.id.activity_main_continuously_sample_button) }

    val composeButton = KButton { withId(R.id.activity_main_complex_compose_sample_button) }

    val idleWaitingButton = KButton { withId(R.id.activity_main_idlewaiting_sample_button) }

    val descriptionText = KTextView { withId(R.id.activity_main_title) }
}
