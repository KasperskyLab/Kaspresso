@file:Suppress("unused")
package com.kaspersky.components.kautomator.component.common.actions

import androidx.test.uiautomator.Direction
import com.kaspersky.components.kautomator.intercept.operation.UiOperationType

/**
 * Provides swipeable actions for UiSwipeView
 */
interface UiSwipeableActions : UiBaseActions {

    /**
     * Swipes left on the view
     * @param percent The length of the swipe as a percentage of this object's size.
     */
    fun swipeLeft(percent: Float = 0.95f) {
        view.perform(UiSwipeableActionType.SWIPE_LEFT) { swipe(Direction.LEFT, percent) }
    }

    /**
     * Swipes right on the view
     * @param percent The length of the swipe as a percentage of this object's size.
     */
    fun swipeRight(percent: Float = 0.95f) {
        view.perform(UiSwipeableActionType.SWIPE_RIGHT) { swipe(Direction.RIGHT, percent) }
    }

    /**
     * Swipes up on the view
     * @param percent The length of the swipe as a percentage of this object's size.
     */
    fun swipeUp(percent: Float = 0.95f) {
        view.perform(UiSwipeableActionType.SWIPE_UP) { swipe(Direction.UP, percent) }
    }

    /**
     * Swipes down on the view
     * @param percent The length of the swipe as a percentage of this object's size.
     */
    fun swipeDown(percent: Float = 0.95f) {
        view.perform(UiSwipeableActionType.SWIPE_DOWN) { swipe(Direction.DOWN, percent) }
    }

    enum class UiSwipeableActionType : UiOperationType {
        SWIPE_LEFT,
        SWIPE_RIGHT,
        SWIPE_UP,
        SWIPE_DOWN
    }
}