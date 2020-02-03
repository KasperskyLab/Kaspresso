@file:Suppress("unused")
package com.kaspersky.components.kautomator.dsl.chip

import com.kaspersky.components.kautomator.dsl.common.builders.UiViewBuilder
import com.kaspersky.components.kautomator.dsl.common.builders.UiViewSelector
import com.kaspersky.components.kautomator.dsl.common.views.UiBaseView

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