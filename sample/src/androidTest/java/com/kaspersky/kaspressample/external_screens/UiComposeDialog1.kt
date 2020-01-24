package com.kaspersky.kaspressample.external_screens

import com.kaspersky.components.kautomator.dsl.text.UiButton
import com.kaspersky.components.kautomator.dsl.text.UiTextView
import com.kaspersky.kaspressample.R

object UiComposeDialog1 : UiSampleScreen<UiComposeDialog1>() {

    val title = UiTextView {
        withText(this@UiComposeDialog1.context.getString(R.string.compose_screen_dialog_title_1))
    }

    val okButton = UiButton {
        withText(this@UiComposeDialog1.context.getString(R.string.compose_screen_dialog_pos_button))
    }
}