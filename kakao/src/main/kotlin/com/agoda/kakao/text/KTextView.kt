@file:Suppress("unused")

package com.agoda.kakao.text

import android.view.View
import com.agoda.kakao.common.builders.ViewBuilder
import com.agoda.kakao.common.views.KBaseView
import com.agoda.kakao.delegates.DataInteractionDelegate
import org.hamcrest.Matcher

/**
 * View with BaseActions and TextViewAssertions
 *
 * @see BaseActions
 * @see TextViewActions
 * @see TextViewAssertions
 */
class KTextView : KBaseView<KTextView>, TextViewActions, TextViewAssertions {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataInteractionDelegate, function: ViewBuilder.() -> Unit) : super(parent, function)
}