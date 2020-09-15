@file:Suppress("unused")
package com.kaspersky.components.kautomator.component.common.views

import com.kaspersky.components.kautomator.component.common.builders.UiViewBuilder
import com.kaspersky.components.kautomator.component.common.builders.UiViewSelector

/**
 * Simple view with [UiBaseAction][com.kaspersky.components.kautomator.component.common.actions.UiBaseActions] and
 * [UiBaseAssertion][com.kaspersky.components.kautomator.component.common.assertions.UiBaseAssertions]
 *
 * @see com.kaspersky.components.kautomator.component.common.actions.UiBaseActions
 * @see com.kaspersky.components.kautomator.component.common.assertions.UiBaseAssertions
 */
class UiView : UiBaseView<UiView> {
    constructor(selector: UiViewSelector) : super(selector)
    constructor(func: UiViewBuilder.() -> Unit) : super(func)
}
