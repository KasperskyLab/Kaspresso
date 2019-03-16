@file:Suppress("unused")

package com.agoda.kakao.common.actions

import android.support.test.espresso.action.ViewActions

/**
 * Provides swipe actions for views
 */
interface SwipeableActions : BaseActions {
    /**
     * Swipes left on the view
     */
    fun swipeLeft() {
        view.perform(ViewActions.swipeLeft())
    }

    /**
     * Swipes right on the view
     */
    fun swipeRight() {
        view.perform(ViewActions.swipeRight())
    }

    /**
     * Swipes up on the view
     */
    fun swipeUp() {
        view.perform(ViewActions.swipeUp())
    }

    /**
     * Swipes down on the view
     */
    fun swipeDown() {
        view.perform(ViewActions.swipeDown())
    }
}