package com.kaspersky.components.uiautomator_dsl.dsl.common.views

import com.kaspersky.components.uiautomator_dsl.dsl.common.builders.UiViewBuilder

/**
 * Simple view with UiBaseActions and UiBaseAssertions
 *
 * @see com.kaspersky.components.uiautomator_dsl.dsl.common.actions.UiBaseActions
 * @see com.kaspersky.components.uiautomator_dsl.dsl.common.assertions.UiBaseAssertions
 */
class UiView : UiBaseView<UiView> {
    constructor(func: UiViewBuilder.() -> Unit) : super(func)
}