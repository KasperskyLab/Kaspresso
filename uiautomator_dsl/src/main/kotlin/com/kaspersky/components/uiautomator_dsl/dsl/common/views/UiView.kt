package com.kaspersky.components.uiautomator_dsl.dsl.common.views

import androidx.test.uiautomator.BySelector
import com.kaspersky.components.uiautomator_dsl.dsl.common.builders.UiViewBuilder

/**
 * Simple view with [UiBaseAction][com.kaspersky.components.uiautomator_dsl.dsl.common.actions.UiBaseActions] and
 * [UiBaseAssertion][com.kaspersky.components.uiautomator_dsl.dsl.common.assertions.UiBaseAssertions]
 *
 * @see com.kaspersky.components.uiautomator_dsl.dsl.common.actions.UiBaseActions
 * @see com.kaspersky.components.uiautomator_dsl.dsl.common.assertions.UiBaseAssertions
 */
class UiView : UiBaseView<UiView> {
    constructor(selector: BySelector) : super(selector)
    constructor(func: UiViewBuilder.() -> Unit) : super(func)
}