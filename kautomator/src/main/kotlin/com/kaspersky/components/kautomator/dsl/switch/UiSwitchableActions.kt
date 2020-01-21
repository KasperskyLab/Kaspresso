package com.kaspersky.components.kautomator.dsl.switch

import com.kaspersky.components.kautomator.dsl.common.actions.UiBaseActions
import com.kaspersky.components.kautomator.dsl.switch.UiSwitchableActions.SwitchableUiActionType.SWITCH
import com.kaspersky.components.kautomator.intercepting.operation.UiOperationType

/**
 * Provides switchable actions for UiSwitchView
 */
interface UiSwitchableActions : UiBaseActions {

    enum class Direction { RIGHT, LEFT }

    /**
     * Moves the thumb of the switch to the right
     *
     * @param direction for the thumb to move
     */
    fun swipeSwitchThumb(direction: Direction) {
        view.perform(SWITCH, "direction=$direction") {
            val uiDirection: androidx.test.uiautomator.Direction =
                if (direction == Direction.RIGHT)
                    androidx.test.uiautomator.Direction.RIGHT
                else
                    androidx.test.uiautomator.Direction.LEFT
            swipe(uiDirection, 1f)
        }
    }

    enum class SwitchableUiActionType : UiOperationType {
        SWITCH
    }
}