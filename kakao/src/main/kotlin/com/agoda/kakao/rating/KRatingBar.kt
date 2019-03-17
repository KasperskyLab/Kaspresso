@file:Suppress("unused")

package com.agoda.kakao.rating

import android.view.View
import com.agoda.kakao.common.builders.ViewBuilder
import com.agoda.kakao.common.views.KBaseView
import com.agoda.kakao.delegates.DataInteractionDelegate
import org.hamcrest.Matcher

/**
 * View with RatingBarActions and RatingBarAssertions
 *
 * @see RatingBarActions
 * @see RatingBarAssertions
 */
class KRatingBar : KBaseView<KRatingBar>, RatingBarActions, RatingBarAssertions {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataInteractionDelegate, function: ViewBuilder.() -> Unit) : super(parent, function)
}