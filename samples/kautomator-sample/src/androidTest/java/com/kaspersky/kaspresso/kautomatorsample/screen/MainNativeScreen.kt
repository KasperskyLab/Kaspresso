package com.kaspersky.kaspresso.kautomatorsample.screen

import com.kaspersky.components.kautomator.component.check.UiCheckBox
import com.kaspersky.components.kautomator.component.common.views.UiView
import com.kaspersky.components.kautomator.component.edit.UiEditText
import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.components.kautomator.component.text.UiTextView
import com.kaspersky.components.kautomator.screen.UiScreen
import com.kaspersky.kaspresso.kautomatorsample.R

object MainNativeScreen : UiScreen<MainNativeScreen>() {

    override val packageName: String = "com.kaspersky.kaspresso.kautomatorsample"

    val header = UiTextView { withText(R.string.main_activity_text) }
    val subHeader = UiTextView { textStartsWith(R.string.main_activity_text_start) }
    val image = UiView { withContentDescription(R.string.main_activity_image_description) }
    val simpleEditText = UiEditText { withId(R.id.editText) }
    val simpleButton = UiButton { withId(R.id.button) }
    val checkBox = UiCheckBox { withId(R.id.checkBox) }
}
