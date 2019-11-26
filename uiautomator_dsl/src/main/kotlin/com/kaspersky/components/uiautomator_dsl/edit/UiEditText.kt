package com.kaspersky.components.uiautomator_dsl.edit

import com.kaspersky.components.uiautomator_dsl.common.builders.UiViewBuilder
import com.kaspersky.components.uiautomator_dsl.common.views.UiBaseView
import com.kaspersky.components.uiautomator_dsl.text.UiTextViewAssertions

class UiEditText : UiBaseView<UiEditText>, UiEditableActions, UiTextViewAssertions {
    constructor(builder: UiViewBuilder.() -> Unit) : super(builder)
}