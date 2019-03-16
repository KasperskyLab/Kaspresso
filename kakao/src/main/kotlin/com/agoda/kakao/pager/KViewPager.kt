@file:Suppress("unused")

package com.agoda.kakao.pager

import android.view.View
import com.agoda.kakao.common.actions.SwipeableActions
import com.agoda.kakao.common.builders.ViewBuilder
import com.agoda.kakao.common.views.KBaseView
import com.agoda.kakao.delegates.DataInteractionDelegate
import org.hamcrest.Matcher

/**
 * View with SwipeableActions and ViewPagerAssertions
 *
 * @see SwipeableActions
 * @see ViewPagerAssertions
 */
class KViewPager : KBaseView<KViewPager>, SwipeableActions, ViewPagerAssertions {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataInteractionDelegate, function: ViewBuilder.() -> Unit) : super(parent, function)
}