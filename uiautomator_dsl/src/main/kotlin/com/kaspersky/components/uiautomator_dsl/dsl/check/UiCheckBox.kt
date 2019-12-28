package com.kaspersky.components.uiautomator_dsl.dsl.check

import androidx.test.uiautomator.BySelector
import com.kaspersky.components.uiautomator_dsl.dsl.common.builders.UiViewBuilder
import com.kaspersky.components.uiautomator_dsl.dsl.common.views.UiBaseView

/**
 * Ui View with [UiCheckableActions] and [UiCheckableAssertions]
 *
 * @see UiCheckableActions
 * @see UiCheckableAssertions
 */
class UiCheckBox : UiBaseView<UiCheckBox>, UiCheckableActions, UiCheckableAssertions {
    constructor(selector: BySelector) : super(selector)
    constructor(builder: UiViewBuilder.() -> Unit) : super(builder)
}