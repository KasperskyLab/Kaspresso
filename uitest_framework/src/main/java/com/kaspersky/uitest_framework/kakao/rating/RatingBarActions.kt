@file:Suppress("unused")

package com.kaspersky.uitest_framework.kakao.rating

import android.view.View
import android.widget.RatingBar
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.ViewMatchers
import com.kaspersky.uitest_framework.kakao.common.actions.BaseActions

/**
 * Provides action for RatingBar
 */
interface RatingBarActions : BaseActions {
    /**
     * Set rating for RatingBar
     *
     * @param number rating to set for the RatingBar
     */
    fun setRatingAt(number: Float) {
        view.perform(object : ViewAction {
            override fun getDescription() = "set rating value of rating bar as: $number"

            override fun getConstraints() = ViewMatchers.isAssignableFrom(RatingBar::class.java)

            override fun perform(uiController: UiController, view: View) {
                if (view is RatingBar) {
                    view.rating = number
                }
            }
        })
    }
}