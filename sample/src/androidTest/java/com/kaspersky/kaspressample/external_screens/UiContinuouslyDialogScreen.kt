package com.kaspersky.kaspressample.external_screens

import androidx.appcompat.app.AlertDialog
import com.kaspersky.components.kautomator.component.text.UiTextView
import com.kaspersky.kaspressample.R

object UiContinuouslyDialogScreen : UiSampleScreen<UiContinuouslyDialogScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = AlertDialog::class.java

    val dialogTitle = UiTextView {
        withText(this@UiContinuouslyDialogScreen.context.getString(R.string.continuously_dialog_title))
    }
}