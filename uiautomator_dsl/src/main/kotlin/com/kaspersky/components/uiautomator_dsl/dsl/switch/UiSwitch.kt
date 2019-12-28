package com.kaspersky.components.uiautomator_dsl.dsl.switch

import androidx.test.uiautomator.BySelector
import com.kaspersky.components.uiautomator_dsl.dsl.common.assertions.UiBaseAssertions
import com.kaspersky.components.uiautomator_dsl.dsl.common.builders.UiViewBuilder
import com.kaspersky.components.uiautomator_dsl.dsl.common.views.UiBaseView

/**
 * Ui View with [UiSwitchableActions] and [UiBaseAssertions]
 *
 * @see UiSwitchableActions
 * @see UiBaseAssertions
 */
class UiSwitch : UiBaseView<UiSwitch>, UiSwitchableActions, UiBaseAssertions {
    constructor(selector: BySelector) : super(selector)
    constructor(builder: UiViewBuilder.() -> Unit) : super(builder)
}