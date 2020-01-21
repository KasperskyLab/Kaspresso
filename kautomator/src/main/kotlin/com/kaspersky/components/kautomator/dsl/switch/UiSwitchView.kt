package com.kaspersky.components.kautomator.dsl.switch

import com.kaspersky.components.kautomator.dsl.common.assertions.UiBaseAssertions
import com.kaspersky.components.kautomator.dsl.common.builders.UiViewBuilder
import com.kaspersky.components.kautomator.dsl.common.builders.UiViewSelector
import com.kaspersky.components.kautomator.dsl.common.views.UiBaseView

/**
 * Ui View with [UiSwitchableActions] and [UiBaseAssertions]
 *
 * @see UiSwitchableActions
 * @see UiBaseAssertions
 */
class UiSwitchView : UiBaseView<UiSwitchView>, UiSwitchableActions, UiBaseAssertions {
    constructor(selector: UiViewSelector) : super(selector)
    constructor(builder: UiViewBuilder.() -> Unit) : super(builder)
}