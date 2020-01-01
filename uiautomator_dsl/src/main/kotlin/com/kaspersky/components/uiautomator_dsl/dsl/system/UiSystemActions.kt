package com.kaspersky.components.uiautomator_dsl.dsl.system

import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiActionType
import com.kaspersky.components.uiautomator_dsl.intercepting.delegate.UiDeviceDelegate
import com.kaspersky.components.uiautomator_dsl.dsl.system.UiSystemActions.SystemActionType.*

/**
 * Interface with common actions providing by UiAutomator and executing in the System
 *
 * Provides basic actions that can be performed everywhere
 *
 * @property view UiDeviceDelegate on which all actions are performed
 */
interface UiSystemActions {

    val view: UiDeviceDelegate

    /**
     * Opens the notification shade
     */
    fun openNotification() {
        view.perform(OPEN_NOTIFICATION) {
            checkBooleanAction(OPEN_NOTIFICATION) { openNotification() }
        }
    }

    /**
     * Opens the Quick Settings shade
     */
    fun openQuickSettings() {
        view.perform(OPEN_QUICK_SETTINGS) {
            checkBooleanAction(OPEN_QUICK_SETTINGS) { openQuickSettings() }
        }
    }

    /**
     * Simulates a short press on the DELETE key
     */
    fun pressDelete() {
        view.perform(PRESS_DELETE) {
            checkBooleanAction(PRESS_DELETE) { pressDelete() }
        }
    }

    /**
     * Simulates a short press on the ENTER key
     */
    fun pressEnter() {
        view.perform(PRESS_ENTER) {
            checkBooleanAction(PRESS_ENTER) { pressEnter() }
        }
    }

    /**
     * Simulates a short press on the HOME button
     */
    fun pressHome() {
        view.perform(PRESS_HOME) {
            checkBooleanAction(PRESS_HOME) { pressHome() }
        }
    }

    /**
     * Simulates a short press on the MENU button
     */
    fun pressMenu() {
        view.perform(PRESS_MENU) {
            checkBooleanAction(PRESS_MENU) { pressMenu() }
        }
    }

    /**
     * Simulates a short press on the Recent Apps button
     */
    fun pressRecentApps() {
        view.perform(PRESS_RECENT_APPS) {
            checkBooleanAction(PRESS_RECENT_APPS) { pressRecentApps() }
        }
    }

    /**
     * Simulates a short press on the SEARCH button
     */
    fun pressSearch() {
        view.perform(PRESS_SEARCH) {
            checkBooleanAction(PRESS_SEARCH) { pressSearch() }
        }
    }

    /**
     * Perform a click at arbitrary coordinates specified by the user (click by System)
     *
     * @param x coordinate
     * @param y coordinate
     */
    fun click(x: Int, y: Int) {
        view.perform(CLICK) {
            checkBooleanAction("$CLICK(x=$x, y=$y)") { click(x, y) }
        }
    }

    /**
     * Performs a swipe from one coordinate to another coordinate. You can control
     * the smoothness and speed of the swipe by specifying the number of steps.
     * Each step execution is throttled to 5 milliseconds per step, so for a 100
     * steps, the swipe will take around 0.5 seconds to complete.
     *
     * @param startX X-axis value for the starting coordinate
     * @param startY Y-axis value for the starting coordinate
     * @param endX X-axis value for the ending coordinate
     * @param endY Y-axis value for the ending coordinate
     * @param steps is the number of steps for the swipe action
     */
    fun drag(startX: Int, startY: Int, endX: Int, endY: Int, steps: Int) {
        view.perform(DRAG) {
            checkBooleanAction(
                "$DRAG(startX=$startX, startY=$startY, endX=$endX, endY=$endY, steps=$steps)") {
                drag(startX, startY, endX, endY, steps)
            }
        }
    }

    /**
     * This method simply presses the power button if the screen is ON else
     * it does nothing if the screen is already OFF.
     */
    fun sleep() {
        view.perform(SLEEP) {
            checkAction(SLEEP) { sleep() }
        }
    }

    /**
     * This method simulates pressing the power button if the screen is OFF else
     * it does nothing if the screen is already ON.
     */
    fun wakeUp() {
        view.perform(WAKE_UP) {
            checkAction(WAKE_UP) { wakeUp() }
        }
    }

    private fun checkBooleanAction(methodName: String, action: () -> Boolean) {
        val result = action.invoke()
        if (!result) throw AssertionError("$methodName method in UiAutomator hasn't performed")
    }

    private fun checkBooleanAction(methodName: UiActionType, action: () -> Boolean) =
        checkBooleanAction(methodName.name, action)

    private fun checkAction(methodName: UiActionType, action: () -> Unit) {
        try {
            action.invoke()
        } catch (exception: Exception) {
            throw AssertionError("$methodName method in UiAutomator hasn't performed", exception)
        }
    }

    enum class SystemActionType : UiActionType {
        OPEN_NOTIFICATION,
        OPEN_QUICK_SETTINGS,
        PRESS_DELETE,
        PRESS_ENTER,
        PRESS_HOME,
        PRESS_MENU,
        PRESS_RECENT_APPS,
        PRESS_SEARCH,
        CLICK,
        DRAG,
        SLEEP,
        WAKE_UP
    }
}