package com.kaspersky.components.uiautomator_dsl.dsl.swipe

import androidx.test.uiautomator.BySelector
import com.kaspersky.components.uiautomator_dsl.dsl.common.assertions.UiBaseAssertions
import com.kaspersky.components.uiautomator_dsl.dsl.common.builders.UiViewBuilder
import com.kaspersky.components.uiautomator_dsl.dsl.common.views.UiBaseView

/**
 * Ui View with [UiSwipeableActions] and [UiBaseAssertions]
 *
 * @see UiSwipeableActions
 * @see UiBaseAssertions
 */
class UiSwipeView : UiBaseView<UiSwipeView>, UiSwipeableActions, UiBaseAssertions {
    constructor(selector: BySelector) : super(selector)
    constructor(builder: UiViewBuilder.() -> Unit) : super(builder)
}