package com.kaspersky.components.uiautomator_dsl.dsl.text

import androidx.test.uiautomator.BySelector
import com.kaspersky.components.uiautomator_dsl.dsl.common.builders.UiViewBuilder
import com.kaspersky.components.uiautomator_dsl.dsl.common.views.UiBaseView
import com.kaspersky.components.uiautomator_dsl.dsl.edit.UiEditableActions

/**
 * Ui View with UiBaseActions and UiTextViewAssertions
 *
 * @see com.kaspersky.components.uiautomator_dsl.dsl.common.actions.UiBaseActions
 * @see UiTextViewAssertions
 */
class UiButton : UiBaseView<UiButton>, UiTextViewAssertions {
    constructor(selector: BySelector) : super(selector)
    constructor(builder: UiViewBuilder.() -> Unit) : super(builder)
}