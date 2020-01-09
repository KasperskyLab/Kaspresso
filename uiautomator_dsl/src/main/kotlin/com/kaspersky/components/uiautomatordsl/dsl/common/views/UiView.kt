package com.kaspersky.components.uiautomatordsl.dsl.common.views

import com.kaspersky.components.uiautomatordsl.dsl.common.builders.UiViewBuilder
import com.kaspersky.components.uiautomatordsl.dsl.common.builders.UiViewSelector

/**
 * Simple view with [UiBaseAction][com.kaspersky.components.uiautomatordsl.dsl.common.actions.UiBaseActions] and
 * [UiBaseAssertion][com.kaspersky.components.uiautomatordsl.dsl.common.assertions.UiBaseAssertions]
 *
 * @see com.kaspersky.components.uiautomatordsl.dsl.common.actions.UiBaseActions
 * @see com.kaspersky.components.uiautomatordsl.dsl.common.assertions.UiBaseAssertions
 */
class UiView : UiBaseView<UiView> {
    constructor(selector: UiViewSelector) : super(selector)
    constructor(func: UiViewBuilder.() -> Unit) : super(func)
}