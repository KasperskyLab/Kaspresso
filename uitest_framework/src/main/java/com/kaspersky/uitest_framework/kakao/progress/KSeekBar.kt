@file:Suppress("unused")

package com.kaspersky.uitest_framework.kakao.progress

import android.view.View
import com.kaspersky.uitest_framework.kakao.dispatchers.DataDispatcher
import com.kaspersky.uitest_framework.kakao.common.builders.ViewBuilder
import com.kaspersky.uitest_framework.kakao.common.views.KBaseView
import org.hamcrest.Matcher

/**
 * View with SeekBarActions and ProgressBarAssertions
 *
 * @see SeekBarActions
 * @see ProgressBarAssertions
 */
class KSeekBar : KBaseView<KSeekBar>, SeekBarActions, ProgressBarAssertions {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataDispatcher, function: ViewBuilder.() -> Unit) : super(parent, function)
}