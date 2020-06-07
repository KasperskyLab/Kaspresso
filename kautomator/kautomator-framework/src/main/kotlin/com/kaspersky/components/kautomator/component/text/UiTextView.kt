@file:Suppress("unused")
package com.kaspersky.components.kautomator.component.text

import com.kaspersky.components.kautomator.component.common.assertions.UiBaseAssertions
import com.kaspersky.components.kautomator.component.common.builders.UiViewBuilder
import com.kaspersky.components.kautomator.component.common.builders.UiViewSelector
import com.kaspersky.components.kautomator.component.common.views.UiBaseView

/**
 * Ui View with UiBaseActions and UiTextViewAssertions
 *
 * @see com.kaspersky.components.kautomator.component.common.actions.UiBaseActions
 * @see UiTextViewAssertions
 */
class UiTextView : UiBaseView<UiTextView>, UiBaseAssertions, UiTextViewAssertions {
    constructor(selector: UiViewSelector) : super(selector)
    constructor(builder: UiViewBuilder.() -> Unit) : super(builder)
}
