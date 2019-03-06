@file:Suppress("unused")

package com.kaspersky.uitest_framework.kakao.tabs

import android.view.View
import com.kaspersky.uitest_framework.kakao.dispatchers.DataDispatcher
import com.kaspersky.uitest_framework.kakao.common.builders.ViewBuilder
import com.kaspersky.uitest_framework.kakao.common.views.KBaseView
import org.hamcrest.Matcher

/**
 * View with TabLayoutActions and TabLayoutAssertions
 *
 * @see TabLayoutActions
 * @see TabLayoutAssertions
 */
class KTabLayout : KBaseView<KTabLayout>, TabLayoutActions, TabLayoutAssertions {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataDispatcher, function: ViewBuilder.() -> Unit) : super(parent, function)
}