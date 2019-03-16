@file:Suppress("unused")

package com.agoda.kakao.edit

import android.support.design.widget.TextInputEditText
import android.view.View
import com.agoda.kakao.common.builders.ViewBuilder
import com.agoda.kakao.common.views.KBaseView
import com.agoda.kakao.delegates.DataInteractionDelegate
import org.hamcrest.Matcher

/**
 * View with TextInputLayoutAssertions
 *
 * @see TextInputLayoutAssertions
 */
class KTextInputLayout : KBaseView<KTextInputLayout>, TextInputLayoutAssertions {
    val edit: KEditText

    constructor(function: ViewBuilder.() -> Unit) : super(function) {
        edit = KEditText {
            isDescendantOfA(function)
            isInstanceOf(TextInputEditText::class.java)
        }
    }

    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function) {
        edit = KEditText {
            isDescendantOfA(function)
            isInstanceOf(TextInputEditText::class.java)
        }
    }

    constructor(parent: DataInteractionDelegate, function: ViewBuilder.() -> Unit) : super(parent, function) {
        edit = KEditText {
            isDescendantOfA(function)
            isInstanceOf(TextInputEditText::class.java)
        }
    }
}