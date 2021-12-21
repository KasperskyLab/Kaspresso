@file:Suppress("unused")

package com.kaspersky.components.kautomator.component.scroll

import androidx.test.uiautomator.UiScrollable
import com.kaspersky.components.kautomator.component.common.actions.UiScrollableActions
import com.kaspersky.components.kautomator.component.common.actions.UiSwipeableActions
import com.kaspersky.components.kautomator.component.common.builders.UiViewBuilder
import com.kaspersky.components.kautomator.component.common.builders.UiViewSelector
import com.kaspersky.components.kautomator.component.common.views.UiBaseView

class UiHorizontalScrollView: UiBaseView<UiScrollView>, UiSwipeableActions, UiScrollableActions {
    constructor(selector: UiViewSelector) : super(selector)
    constructor(builder: UiViewBuilder.() -> Unit) : super(builder)

    override val setUiScrollableOrientation: UiScrollable.() -> UiScrollable
        get() = { setAsHorizontalList() }
}

