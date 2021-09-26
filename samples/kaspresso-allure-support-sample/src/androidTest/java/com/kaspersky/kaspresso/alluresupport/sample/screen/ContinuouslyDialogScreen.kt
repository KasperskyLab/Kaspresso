package com.kaspersky.kaspresso.alluresupport.sample.screen

import com.kaspersky.kaspresso.R
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KTextView

object ContinuouslyDialogScreen : KScreen<ContinuouslyDialogScreen>() {
    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val dialogTitle = KTextView {
        withText(R.string.continuously_dialog_title)
    }
}
