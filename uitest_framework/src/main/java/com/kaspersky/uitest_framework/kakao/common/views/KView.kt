@file:Suppress("unused")

package com.kaspersky.uitest_framework.kakao.common.views

import android.view.View
import com.kaspersky.uitest_framework.kakao.dispatchers.DataDispatcher
import com.kaspersky.uitest_framework.kakao.common.builders.ViewBuilder
import org.hamcrest.Matcher

/**
 * Simple view with BaseActions and BaseAssertions
 *
 * @see BaseActions
 * @see BaseAssertions
 */
class KView : KBaseView<KView> {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataDispatcher, function: ViewBuilder.() -> Unit) : super(parent, function)
}