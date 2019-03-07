@file:Suppress("unused")

package com.kaspersky.uitest_framework.kakao.common.views

import android.view.View
import com.kaspersky.uitest_framework.kakao.delegates.DataInteractionDelegate
import com.kaspersky.uitest_framework.kakao.common.actions.SwipeableActions
import com.kaspersky.uitest_framework.kakao.common.builders.ViewBuilder
import org.hamcrest.Matcher

/**
 * View with SwipeableActions and BaseAssertions
 *
 * @see SwipeableActions
 * @see BaseAssertions
 */
class KSwipeView : KBaseView<KSwipeView>, SwipeableActions {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataInteractionDelegate, function: ViewBuilder.() -> Unit) : super(parent, function)
}