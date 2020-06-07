@file:Suppress("unused")
package com.kaspersky.components.kautomator.component.dialog

import com.kaspersky.components.kautomator.component.common.views.UiBaseView
import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.components.kautomator.component.text.UiTextView

/**
 * View for acting and asserting on default AlertDialog
 */
class UiAlertDialog(private val packageName: String) : UiBaseView<UiAlertDialog>({ withPackage(packageName) }) {

    companion object {
        private const val TITLE_ID = "alertTitle"
        private const val MESSAGE_ID = "android:id/message"
        private const val POSITIVE_BTN_ID = "android:id/button1"
        private const val NEGATIVE_BUTTON_ID = "android:id/button2"
        private const val NEUTRAL_BUTTON_ID = "android:id/button3"
    }

    val positiveButton = UiButton { withResourceName(POSITIVE_BTN_ID) }
    val negativeButton = UiButton { withResourceName(NEGATIVE_BUTTON_ID) }
    val neutralButton = UiButton { withResourceName(NEUTRAL_BUTTON_ID) }
    val title = UiTextView { withId(this@UiAlertDialog.packageName, TITLE_ID) }
    val message = UiTextView { withResourceName(MESSAGE_ID) }
}
