@file:Suppress("unused")

package com.kaspersky.uitest_framework.kakao.swiperefresh

import android.view.View
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.ViewMatchers
import android.support.v4.widget.SwipeRefreshLayout
import com.kaspersky.uitest_framework.kakao.common.actions.SwipeableActions

/**
 * Provides actions for SwipeRefreshLayout
 */
interface SwipeRefreshLayoutActions : SwipeableActions {
    /**
     * Sets the refreshing state of SwipeRefreshLayout
     *
     * @param refreshing state to be set
     */
    fun setRefreshing(refreshing: Boolean) {
        view.perform(object : ViewAction {
            override fun getDescription() = "Sets the refreshing state to $refreshing"

            override fun getConstraints() = ViewMatchers.isAssignableFrom(SwipeRefreshLayout::class.java)

            override fun perform(uiController: UiController, view: View) {
                if (view is SwipeRefreshLayout) {
                    view.isRefreshing = refreshing
                }
            }
        })
    }
}