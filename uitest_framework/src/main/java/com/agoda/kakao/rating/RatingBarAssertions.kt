@file:Suppress("unused")

package com.agoda.kakao.rating

import android.support.test.espresso.assertion.ViewAssertions
import com.agoda.kakao.common.assertions.BaseAssertions
import com.agoda.kakao.common.matchers.RatingBarMatcher

/**
 * Provides assertions for RatingBar
 */
interface RatingBarAssertions : BaseAssertions {
    /**
     *  Checks if RatingBar has number of rating as expected
     *
     *  @param number rating as expected
     */
    fun hasRating(number: Float) {
        view.check(ViewAssertions.matches(RatingBarMatcher(number)))
    }
}