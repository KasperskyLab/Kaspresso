package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.tutorial.R
import com.kaspersky.kaspresso.tutorial.flaky.ResultActivity
import io.github.kakaocup.kakao.text.KTextView

object ResultScreen: KScreen<ResultScreen>() {
    override val layoutId: Int = R.layout.result_activity
    override val viewClass: Class<*> = ResultActivity::class.java

    val title = KTextView { withId(R.id.result_title) }
}
