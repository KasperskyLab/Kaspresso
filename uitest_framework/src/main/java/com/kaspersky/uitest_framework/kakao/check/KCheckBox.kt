@file:Suppress("unused")

package com.kaspersky.uitest_framework.kakao.check

import android.view.View
import com.kaspersky.uitest_framework.kakao.dispatchers.DataDispatcher
import com.kaspersky.uitest_framework.kakao.common.builders.ViewBuilder
import com.kaspersky.uitest_framework.kakao.common.views.KBaseView
import com.kaspersky.uitest_framework.kakao.text.TextViewAssertions
import org.hamcrest.Matcher

/**
 * View with CheckableActions, CheckableAssertions and TextViewAssertions
 *
 * @see CheckableActions
 * @see CheckableAssertions
 * @see TextViewAssertions
 */
class KCheckBox : KBaseView<KCheckBox>, CheckableActions, TextViewAssertions, CheckableAssertions {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataDispatcher, function: ViewBuilder.() -> Unit) : super(parent, function)
}