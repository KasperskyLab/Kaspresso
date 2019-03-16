@file:Suppress("unused")

package com.agoda.kakao.edit

import android.view.View
import com.agoda.kakao.common.builders.ViewBuilder
import com.agoda.kakao.common.views.KBaseView
import com.agoda.kakao.delegates.DataInteractionDelegate
import org.hamcrest.Matcher

/**
 * View with EditableActions and EditableAssertions
 *
 * @see EditableActions
 * @see EditableAssertions
 */
class KEditText : KBaseView<KEditText>, EditableActions, EditableAssertions {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataInteractionDelegate, function: ViewBuilder.() -> Unit) : super(parent, function)
}