@file:Suppress("unused")
package com.kaspersky.components.kautomator.component.edit

import com.kaspersky.components.kautomator.component.common.builders.UiViewBuilder
import com.kaspersky.components.kautomator.component.common.builders.UiViewSelector
import com.kaspersky.components.kautomator.component.common.views.UiBaseView
import com.kaspersky.components.kautomator.component.text.UiTextViewAssertions

/**
 * Ui View with [UiEditableActions] and [UiTextViewAssertions]
 *
 * @see UiEditableActions
 * @see UiTextViewAssertions
 */
class UiEditText : UiBaseView<UiEditText>, UiEditableActions, UiTextViewAssertions {
    constructor(selector: UiViewSelector) : super(selector)
    constructor(builder: UiViewBuilder.() -> Unit) : super(builder)
}
