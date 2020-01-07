package com.kaspersky.components.uiautomator_dsl.dsl.check

import com.kaspersky.components.uiautomator_dsl.dsl.common.builders.UiViewBuilder
import com.kaspersky.components.uiautomator_dsl.dsl.common.builders.UiViewSelector
import com.kaspersky.components.uiautomator_dsl.dsl.common.views.UiBaseView

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