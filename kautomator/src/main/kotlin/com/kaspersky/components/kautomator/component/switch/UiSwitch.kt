@file:Suppress("unused")
package com.kaspersky.components.kautomator.component.switch

import com.kaspersky.components.kautomator.component.common.assertions.UiBaseAssertions
import com.kaspersky.components.kautomator.component.common.builders.UiViewBuilder
import com.kaspersky.components.kautomator.component.common.builders.UiViewSelector
import com.kaspersky.components.kautomator.component.common.views.UiBaseView

/**
 * Ui View with [UiSwitchableActions] and [UiBaseAssertions]
 *
 * @see UiSwitchableActions
 * @see UiBaseAssertions
 */
class UiSwitch : UiBaseView<UiSwitch>, UiSwitchableActions, UiBaseAssertions {
    constructor(selector: UiViewSelector) : super(selector)
    constructor(builder: UiViewBuilder.() -> Unit) : super(builder)
}