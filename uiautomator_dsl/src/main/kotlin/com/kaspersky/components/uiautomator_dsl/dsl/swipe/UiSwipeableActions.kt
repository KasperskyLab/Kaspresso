package com.kaspersky.components.uiautomator_dsl.dsl.swipe

import com.kaspersky.components.uiautomator_dsl.dsl.common.actions.UiBaseActions
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiActionType
import com.kaspersky.components.uiautomator_dsl.dsl.swipe.UiSwipeableActions.SwipeableActionType.*
import androidx.test.uiautomator.Direction.*

/**
 * Provides swipeable actions for UiSwipeView
 */
interface UiSwipeableActions : UiBaseActions {

    /**
     * Swipes left on the view
     */
    fun swipeLeft() {
        view.perform(SWIPE_LEFT) { swipe(LEFT, 1f) }
    }

    /**
     * Swipes right on the view
     */
    fun swipeRight() {
        view.perform(SWIPE_RIGHT) { swipe(RIGHT, 1f) }
    }

    /**
     * Swipes up on the view
     */
    fun swipeUp() {
        view.perform(SWIPE_UP) { swipe(UP, 1f) }
    }

    /**
     * Swipes down on the view
     */
    fun swipeDown() {
        view.perform(SWIPE_DOWN) { swipe(DOWN, 1f) }
    }

    enum class SwipeableActionType : UiActionType {
        SWIPE_LEFT,
        SWIPE_RIGHT,
        SWIPE_UP,
        SWIPE_DOWN
    }

}