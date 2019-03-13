@file:Suppress("unused")

package com.agoda.kakao.text

import android.support.design.widget.Snackbar
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.AppCompatTextView
import com.agoda.kakao.common.views.KBaseView

/**
 * View with internal TextView and a Button
 *
 * @see Snackbar
 */
class KSnackbar : KBaseView<KSnackbar>({ isInstanceOf(Snackbar.SnackbarLayout::class.java) }) {
    val text = KTextView {
        isDescendantOfA { isInstanceOf(Snackbar.SnackbarLayout::class.java) }
        isInstanceOf(AppCompatTextView::class.java)
    }

    val action = KButton {
        isDescendantOfA { isInstanceOf(Snackbar.SnackbarLayout::class.java) }
        isInstanceOf(AppCompatButton::class.java)
    }
}