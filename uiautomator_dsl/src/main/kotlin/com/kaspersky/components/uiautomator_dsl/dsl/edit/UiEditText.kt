package com.kaspersky.components.uiautomator_dsl.dsl.edit

import com.kaspersky.components.uiautomator_dsl.dsl.common.builders.UiViewBuilder
import com.kaspersky.components.uiautomator_dsl.dsl.common.views.UiBaseView
import com.kaspersky.components.uiautomator_dsl.dsl.text.UiTextViewAssertions

class UiEditText : UiBaseView<UiEditText>, UiEditableActions, UiTextViewAssertions {
    constructor(builder: UiViewBuilder.() -> Unit) : super(builder)
}