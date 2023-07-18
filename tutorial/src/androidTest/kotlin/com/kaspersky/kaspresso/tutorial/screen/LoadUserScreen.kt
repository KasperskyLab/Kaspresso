package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.tutorial.R
import io.github.kakaocup.kakao.progress.KProgressBar
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

object LoadUserScreen : KScreen<LoadUserScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val loadingButton = KButton { withId(R.id.loading_button) }
    val progressBarLoading = KProgressBar { withId(R.id.progress_bar_loading) }
    val username = KTextView { withId(R.id.username) }
    val error = KTextView { withId(R.id.error) }
}
