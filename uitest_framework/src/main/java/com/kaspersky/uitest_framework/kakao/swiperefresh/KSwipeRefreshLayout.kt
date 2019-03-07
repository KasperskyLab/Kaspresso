@file:Suppress("unused")

package com.kaspersky.uitest_framework.kakao.swiperefresh

import android.view.View
import com.kaspersky.uitest_framework.kakao.delegates.DataInteractionDelegate
import com.kaspersky.uitest_framework.kakao.common.builders.ViewBuilder
import com.kaspersky.uitest_framework.kakao.common.views.KBaseView
import org.hamcrest.Matcher

/**
 * View with SwipeRefreshLayoutActions and SwipeRefreshLayoutAssertions
 *
 * @see SwipeRefreshLayoutActions
 * @see SwipeRefreshLayoutAssertions
 */
class KSwipeRefreshLayout : KBaseView<KSwipeRefreshLayout>, SwipeRefreshLayoutActions, SwipeRefreshLayoutAssertions {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataInteractionDelegate, function: ViewBuilder.() -> Unit) : super(parent, function)
}