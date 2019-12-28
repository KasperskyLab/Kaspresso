package com.kaspersky.components.uiautomator_dsl.dsl.swipe

import com.kaspersky.components.uiautomator_dsl.dsl.common.actions.UiBaseActions
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiOperationType
import com.kaspersky.components.uiautomator_dsl.dsl.swipe.UiSwipeableActions.SwipeableOperationType.*
import androidx.test.uiautomator.Direction.*

/**
 * Provides swipeable actions for UiSwipeView
 */
interface UiSwipeableActions : UiBaseActions {

    /**
     * Swipes left on the view
     */
    fun swipeLeft() {
        actionsView.perform(SWIPE_LEFT) { swipe(LEFT, 1f) }
    }

    /**
     * Swipes right on the view
     */
    fun swipeRight() {
        actionsView.perform(SWIPE_RIGHT) { swipe(RIGHT, 1f) }
    }

    /**
     * Swipes up on the view
     */
    fun swipeUp() {
        actionsView.perform(SWIPE_UP) { swipe(UP, 1f) }
    }

    /**
     * Swipes down on the view
     */
    fun swipeDown() {
        actionsView.perform(SWIPE_DOWN) { swipe(DOWN, 1f) }
    }

    enum class SwipeableOperationType : UiOperationType {
        SWIPE_LEFT,
        SWIPE_RIGHT,
        SWIPE_UP,
        SWIPE_DOWN
    }

}