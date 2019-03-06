@file:Suppress("unused")

package com.kaspersky.uitest_framework.kakao.drawer

import android.view.View
import com.kaspersky.uitest_framework.kakao.dispatchers.DataDispatcher
import com.kaspersky.uitest_framework.kakao.common.builders.ViewBuilder
import com.kaspersky.uitest_framework.kakao.common.views.KBaseView
import org.hamcrest.Matcher

/**
 * View with DrawerActions and BaseAssertions
 *
 * @see DrawerActions
 * @see BaseAssertions
 */
class KDrawerView : KBaseView<KDrawerView>, DrawerActions {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataDispatcher, function: ViewBuilder.() -> Unit) : super(parent, function)
}