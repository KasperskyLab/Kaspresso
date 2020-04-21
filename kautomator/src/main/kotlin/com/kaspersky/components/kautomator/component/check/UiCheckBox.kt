@file:Suppress("unused")
package com.kaspersky.components.kautomator.component.check

import com.kaspersky.components.kautomator.component.common.builders.UiViewBuilder
import com.kaspersky.components.kautomator.component.common.builders.UiViewSelector
import com.kaspersky.components.kautomator.component.common.views.UiBaseView

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