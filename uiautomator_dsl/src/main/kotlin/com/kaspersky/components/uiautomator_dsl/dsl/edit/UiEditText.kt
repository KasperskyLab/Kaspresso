package com.kaspersky.components.uiautomator_dsl.dsl.edit

import androidx.test.uiautomator.BySelector
import com.kaspersky.components.uiautomator_dsl.dsl.common.builders.UiViewBuilder
import com.kaspersky.components.uiautomator_dsl.dsl.common.views.UiBaseView
import com.kaspersky.components.uiautomator_dsl.dsl.text.UiTextViewAssertions

/**
 * Ui View with [UiEditableActions] and [UiTextViewAssertions]
 *
 * @see UiEditableActions
 * @see UiTextViewAssertions
 */
class UiEditText : UiBaseView<UiEditText>, UiEditableActions, UiTextViewAssertions {
    constructor(selector: BySelector) : super(selector)
    constructor(builder: UiViewBuilder.() -> Unit) : super(builder)
}