package com.kaspersky.components.kautomator.dsl.swipe

import androidx.test.uiautomator.Direction
import com.kaspersky.components.kautomator.dsl.common.actions.UiBaseActions
import com.kaspersky.components.kautomator.intercepting.operation.UiOperationType

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

    enum class SwipeableActionType : UiOperationType {
        SWIPE_LEFT,
        SWIPE_RIGHT,
        SWIPE_UP,
        SWIPE_DOWN
    }
}