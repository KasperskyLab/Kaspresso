package com.kaspersky.kaspressample.external_screens

import com.kaspersky.components.kautomator.dsl.text.UiTextView
import com.kaspersky.kaspressample.R

object UiContinuouslyDialogScreen : UiSampleScreen<UiContinuouslyDialogScreen>() {

    val dialogTitle = UiTextView {
        withText(this@UiContinuouslyDialogScreen.context.getString(R.string.continuously_dialog_title))
    }
}