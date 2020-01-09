package com.kaspersky.components.uiautomatordsl.dsl.switch

import com.kaspersky.components.uiautomatordsl.dsl.common.assertions.UiBaseAssertions
import com.kaspersky.components.uiautomatordsl.dsl.common.builders.UiViewBuilder
import com.kaspersky.components.uiautomatordsl.dsl.common.builders.UiViewSelector
import com.kaspersky.components.uiautomatordsl.dsl.common.views.UiBaseView

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