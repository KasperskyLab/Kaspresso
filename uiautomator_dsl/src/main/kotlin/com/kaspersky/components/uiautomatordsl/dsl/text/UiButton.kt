package com.kaspersky.components.uiautomatordsl.dsl.text

import com.kaspersky.components.uiautomatordsl.dsl.common.builders.UiViewBuilder
import com.kaspersky.components.uiautomatordsl.dsl.common.builders.UiViewSelector
import com.kaspersky.components.uiautomatordsl.dsl.common.views.UiBaseView

/**
 * Ui View with UiBaseActions and UiTextViewAssertions
 *
 * @see com.kaspersky.components.uiautomatordsl.dsl.common.actions.UiBaseActions
 * @see UiTextViewAssertions
 */
class UiButton : UiBaseView<UiButton>, UiTextViewAssertions {
    constructor(selector: UiViewSelector) : super(selector)
    constructor(builder: UiViewBuilder.() -> Unit) : super(builder)
}