package com.kaspersky.kaspressample.screen

import io.github.kakaocup.kakao.text.KTextView
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspresso.screens.KScreen

object ContinuouslyDialogScreen : KScreen<ContinuouslyDialogScreen>() {
    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val dialogTitle = KTextView {
        withText(R.string.continuously_dialog_title)
    }
}
