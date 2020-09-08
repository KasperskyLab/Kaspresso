@file:Suppress("unused")
package com.kaspersky.components.kautomator.component.bottomnav

import com.kaspersky.components.kautomator.component.common.builders.UiViewBuilder
import com.kaspersky.components.kautomator.component.common.builders.UiViewSelector
import com.kaspersky.components.kautomator.component.common.views.UiBaseView

/**
 * View for acting and asserting on BottomNavigationView
 *
 * @see UiBottomNavigationViewActions
 * @see UiBottomNavigationViewAssertions
 */
class UiBottomNavigationView : UiBaseView<UiBottomNavigationView>, UiBottomNavigationViewActions, UiBottomNavigationViewAssertions {
    constructor(selector: UiViewSelector) : super(selector)
    constructor(builder: UiViewBuilder.() -> Unit) : super(builder)
}
