package com.kaspersky.kaspressample.external_screens

import androidx.appcompat.app.AlertDialog
import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.components.kautomator.component.text.UiTextView
import com.kaspersky.kaspressample.R

object UiComposeDialog2 : UiSampleScreen<UiComposeDialog2>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = AlertDialog::class.java

    val title = UiTextView {
        withText(this@UiComposeDialog2.context.getString(R.string.compose_screen_dialog_title_2))
    }

    val okButton = UiButton {
        withText(this@UiComposeDialog2.context.getString(R.string.compose_screen_dialog_pos_button))
    }
}
