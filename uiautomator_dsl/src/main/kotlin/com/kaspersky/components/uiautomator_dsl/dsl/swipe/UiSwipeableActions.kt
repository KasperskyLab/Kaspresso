package com.kaspersky.components.uiautomator_dsl.dsl.swipe

import androidx.test.uiautomator.Direction
import com.kaspersky.components.uiautomator_dsl.dsl.common.actions.UiBaseActions
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiActionType

/**
 * Provides swipeable actions for UiSwipeView
 */
interface UiSwipeableActions : UiBaseActions {

    /**
     * Swipes left on the view
     */
    fun swipeLeft() {
        view.perform(SwipeableActionType.SWIPE_LEFT) { swipe(Direction.LEFT, 1f) }
    }

    /**
     * Swipes right on the view
     */
    fun swipeRight() {
        view.perform(SwipeableActionType.SWIPE_RIGHT) { swipe(Direction.RIGHT, 1f) }
    }

    /**
     * Swipes up on the view
     */
    fun swipeUp() {
        view.perform(SwipeableActionType.SWIPE_UP) { swipe(Direction.UP, 1f) }
    }

    /**
     * Swipes down on the view
     */
    fun swipeDown() {
        view.perform(SwipeableActionType.SWIPE_DOWN) { swipe(Direction.DOWN, 1f) }
    }

    enum class SwipeableActionType : UiActionType {
        SWIPE_LEFT,
        SWIPE_RIGHT,
        SWIPE_UP,
        SWIPE_DOWN
    }
}