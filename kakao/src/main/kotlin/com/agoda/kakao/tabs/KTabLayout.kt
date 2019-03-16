@file:Suppress("unused")

package com.agoda.kakao.tabs

import android.view.View
import com.agoda.kakao.common.builders.ViewBuilder
import com.agoda.kakao.common.views.KBaseView
import com.agoda.kakao.delegates.DataInteractionDelegate
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
    constructor(parent: DataInteractionDelegate, function: ViewBuilder.() -> Unit) : super(parent, function)
}