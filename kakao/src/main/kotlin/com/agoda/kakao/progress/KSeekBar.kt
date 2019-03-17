@file:Suppress("unused")

package com.agoda.kakao.progress

import android.view.View
import com.agoda.kakao.common.builders.ViewBuilder
import com.agoda.kakao.common.views.KBaseView
import com.agoda.kakao.delegates.DataInteractionDelegate
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
    constructor(parent: DataInteractionDelegate, function: ViewBuilder.() -> Unit) : super(parent, function)
}