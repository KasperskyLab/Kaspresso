@file:Suppress("unused")
package com.kaspersky.components.kautomator.dsl.bottomnav

import com.kaspersky.components.kautomator.dsl.common.builders.UiViewBuilder
import com.kaspersky.components.kautomator.dsl.common.builders.UiViewSelector
import com.kaspersky.components.kautomator.dsl.common.views.UiBaseView

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