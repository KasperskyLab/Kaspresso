@file:Suppress("unused")

package com.kaspersky.uitest_framework.kakao.rating

import android.view.View
import com.kaspersky.uitest_framework.kakao.delegates.DataInteractionDelegate
import com.kaspersky.uitest_framework.kakao.common.builders.ViewBuilder
import com.kaspersky.uitest_framework.kakao.common.views.KBaseView
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