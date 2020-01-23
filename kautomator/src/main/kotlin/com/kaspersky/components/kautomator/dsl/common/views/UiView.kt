@file:Suppress("unused")
package com.kaspersky.components.kautomator.dsl.common.views

import com.kaspersky.components.kautomator.dsl.common.builders.UiViewBuilder
import com.kaspersky.components.kautomator.dsl.common.builders.UiViewSelector

/**
 * Simple view with [UiBaseAction][com.kaspersky.components.kautomator.dsl.common.actions.UiBaseActions] and
 * [UiBaseAssertion][com.kaspersky.components.kautomator.dsl.common.assertions.UiBaseAssertions]
 *
 * @see com.kaspersky.components.kautomator.dsl.common.actions.UiBaseActions
 * @see com.kaspersky.components.kautomator.dsl.common.assertions.UiBaseAssertions
 */
class UiView : UiBaseView<UiView> {
    constructor(selector: UiViewSelector) : super(selector)
    constructor(func: UiViewBuilder.() -> Unit) : super(func)
}