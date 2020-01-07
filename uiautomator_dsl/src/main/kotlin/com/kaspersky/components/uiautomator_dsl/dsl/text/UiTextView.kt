package com.kaspersky.components.uiautomator_dsl.dsl.text

import com.kaspersky.components.uiautomator_dsl.dsl.common.assertions.UiBaseAssertions
import com.kaspersky.components.uiautomator_dsl.dsl.common.builders.UiViewBuilder
import com.kaspersky.components.uiautomator_dsl.dsl.common.builders.UiViewSelector
import com.kaspersky.components.uiautomator_dsl.dsl.common.views.UiBaseView

/**
 * Ui View with UiBaseActions and UiTextViewAssertions
 *
 * @see com.kaspersky.components.uiautomator_dsl.dsl.common.actions.UiBaseActions
 * @see UiTextViewAssertions
 */
class UiTextView : UiBaseView<UiTextView>, UiBaseAssertions, UiTextViewAssertions {
    constructor(selector: UiViewSelector) : super(selector)
    constructor(builder: UiViewBuilder.() -> Unit) : super(builder)
}