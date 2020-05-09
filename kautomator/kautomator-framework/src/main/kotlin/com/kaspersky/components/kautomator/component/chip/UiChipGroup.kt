@file:Suppress("unused")
package com.kaspersky.components.kautomator.component.chip

import com.kaspersky.components.kautomator.component.common.builders.UiViewBuilder
import com.kaspersky.components.kautomator.component.common.builders.UiViewSelector
import com.kaspersky.components.kautomator.component.common.views.UiBaseView

/**
 * View for acting and asserting on ChipGroup
 *
 * @see UiChipGroupActions
 * @see UiChipGroupAssertions
 */
class UiChipGroup : UiBaseView<UiChipGroup>, UiChipGroupActions, UiChipGroupAssertions {
    constructor(selector: UiViewSelector) : super(selector)
    constructor(builder: UiViewBuilder.() -> Unit) : super(builder)
}
