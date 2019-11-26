package com.kaspersky.components.uiautomator_dsl.text

import com.kaspersky.components.uiautomator_dsl.common.builders.UiViewBuilder
import com.kaspersky.components.uiautomator_dsl.common.views.UiBaseView

class UiTextView : UiBaseView<UiTextView>, UiTextViewAssertions {
    constructor(builder: UiViewBuilder.() -> Unit) : super(builder)
}