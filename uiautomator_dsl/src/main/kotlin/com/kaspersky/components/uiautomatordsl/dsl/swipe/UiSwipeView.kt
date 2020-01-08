package com.kaspersky.components.uiautomatordsl.dsl.swipe

import com.kaspersky.components.uiautomatordsl.dsl.common.assertions.UiBaseAssertions
import com.kaspersky.components.uiautomatordsl.dsl.common.builders.UiViewBuilder
import com.kaspersky.components.uiautomatordsl.dsl.common.builders.UiViewSelector
import com.kaspersky.components.uiautomatordsl.dsl.common.views.UiBaseView

/**
 * Ui View with [UiSwipeableActions] and [UiBaseAssertions]
 *
 * @see UiSwipeableActions
 * @see UiBaseAssertions
 */
class UiSwipeView : UiBaseView<UiSwipeView>, UiSwipeableActions, UiBaseAssertions {
    constructor(selector: UiViewSelector) : super(selector)
    constructor(builder: UiViewBuilder.() -> Unit) : super(builder)
}