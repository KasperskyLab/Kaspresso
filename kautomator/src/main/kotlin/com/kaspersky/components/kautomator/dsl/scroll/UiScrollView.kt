@file:Suppress("unused")
package com.kaspersky.components.kautomator.dsl.scroll

import com.kaspersky.components.kautomator.dsl.common.actions.UiScrollableActions
import com.kaspersky.components.kautomator.dsl.common.actions.UiSwipeableActions
import com.kaspersky.components.kautomator.dsl.common.builders.UiViewBuilder
import com.kaspersky.components.kautomator.dsl.common.builders.UiViewSelector
import com.kaspersky.components.kautomator.dsl.common.views.UiBaseView

class UiScrollView : UiBaseView<UiScrollView>, UiSwipeableActions, UiScrollableActions {

    constructor(selector: UiViewSelector) : super(selector)
    constructor(builder: UiViewBuilder.() -> Unit) : super(builder)
}