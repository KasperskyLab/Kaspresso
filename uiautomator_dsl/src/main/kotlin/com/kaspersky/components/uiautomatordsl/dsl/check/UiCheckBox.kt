package com.kaspersky.components.uiautomatordsl.dsl.check

import com.kaspersky.components.uiautomatordsl.dsl.common.builders.UiViewBuilder
import com.kaspersky.components.uiautomatordsl.dsl.common.builders.UiViewSelector
import com.kaspersky.components.uiautomatordsl.dsl.common.views.UiBaseView

/**
 * Ui View with [UiCheckableActions] and [UiCheckableAssertions]
 *
 * @see UiCheckableActions
 * @see UiCheckableAssertions
 */
class UiCheckBox : UiBaseView<UiCheckBox>, UiCheckableActions, UiCheckableAssertions {
    constructor(selector: UiViewSelector) : super(selector)
    constructor(builder: UiViewBuilder.() -> Unit) : super(builder)
}