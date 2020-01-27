package com.kaspersky.kaspressample.external_screens

import com.kaspersky.components.kautomator.dsl.text.UiButton
import com.kaspersky.components.kautomator.dsl.text.UiTextView
import com.kaspersky.kaspressample.R

object UiComposeDialog2 : UiSampleScreen<UiComposeDialog2>() {

    val title = UiTextView {
        withText(this@UiComposeDialog2.context.getString(R.string.compose_screen_dialog_title_2))
    }

    val okButton = UiButton {
        withText(this@UiComposeDialog2.context.getString(R.string.compose_screen_dialog_pos_button))
    }
}