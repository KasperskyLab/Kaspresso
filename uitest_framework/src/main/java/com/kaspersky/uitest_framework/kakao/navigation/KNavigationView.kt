@file:Suppress("unused")

package com.kaspersky.uitest_framework.kakao.navigation

import android.view.View
import com.kaspersky.uitest_framework.kakao.dispatchers.DataDispatcher
import com.kaspersky.uitest_framework.kakao.common.builders.ViewBuilder
import com.kaspersky.uitest_framework.kakao.common.views.KBaseView
import org.hamcrest.Matcher

/**
 * View with NavigationViewActions and NavigationViewAssertions
 *
 * @see NavigationViewActions
 * @see NavigationViewAssertions
 */
class KNavigationView : KBaseView<KNavigationView>, NavigationViewActions, NavigationViewAssertions {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataDispatcher, function: ViewBuilder.() -> Unit) : super(parent, function)
}