@file:Suppress("unused")

package com.agoda.kakao.common.views

import android.view.View
import com.agoda.kakao.common.builders.ViewBuilder
import com.agoda.kakao.delegates.DataInteractionDelegate
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
    constructor(parent: DataInteractionDelegate, function: ViewBuilder.() -> Unit) : super(parent, function)
}