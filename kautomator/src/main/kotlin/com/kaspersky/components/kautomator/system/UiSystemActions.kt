@file:Suppress("unused")
package com.kaspersky.components.kautomator.system

import com.kaspersky.components.kautomator.intercept.delegate.UiDeviceInteractionDelegate
import com.kaspersky.components.kautomator.intercept.operation.UiOperationType

/**
 * Interface with common actions providing by UiAutomator and executing in the System
 *
 * Provides basic actions that can be performed everywhere
 *
 * @property view UiDeviceDelegate on which all actions are performed
 */
interface UiSystemActions {

    val view: UiDeviceInteractionDelegate

    /**
     * Opens the notification shade
     */
    fun openNotification() {
        view.perform(UiSystemActionType.OPEN_NOTIFICATION) {
            checkBooleanAction(UiSystemActionType.OPEN_NOTIFICATION) {
                openNotification()
            }
        }
    }

    /**
     * Opens the Quick Settings shade
     */
    fun openQuickSettings() {
        view.perform(UiSystemActionType.OPEN_QUICK_SETTINGS) {
            checkBooleanAction(UiSystemActionType.OPEN_QUICK_SETTINGS) { openQuickSettings() }
        }
    }

    /**
     * Simulates a short press on the DELETE key
     */
    fun pressDelete() {
        view.perform(UiSystemActionType.PRESS_DELETE) {
            checkBooleanAction(UiSystemActionType.PRESS_DELETE) { pressDelete() }
        }
    }

    /**
     * Simulates a short press on the ENTER key
     */
    fun pressEnter() {
        view.perform(UiSystemActionType.PRESS_ENTER) {
            checkBooleanAction(UiSystemActionType.PRESS_ENTER) { pressEnter() }
        }
    }

    /**
     * Simulates a short press on the HOME button
     */
    fun pressHome() {
        view.perform(UiSystemActionType.PRESS_HOME) {
            checkBooleanAction(UiSystemActionType.PRESS_HOME) { pressHome() }
        }
    }

    /**
     * Simulates a short press on the MENU button
     */
    fun pressMenu() {
        view.perform(UiSystemActionType.PRESS_MENU) {
            checkBooleanAction(UiSystemActionType.PRESS_MENU) { pressMenu() }
        }
    }

    /**
     * Simulates a short press on the Recent Apps button
     */
    fun pressRecentApps() {
        view.perform(UiSystemActionType.PRESS_RECENT_APPS) {
            checkBooleanAction(UiSystemActionType.PRESS_RECENT_APPS) { pressRecentApps() }
        }
    }

    /**
     * Simulates a short press on the SEARCH button
     */
    fun pressSearch() {
        view.perform(UiSystemActionType.PRESS_SEARCH) {
            checkBooleanAction(UiSystemActionType.PRESS_SEARCH) { pressSearch() }
        }
    }

    /**
     * Perform a click at arbitrary coordinates specified by the user (click by System)
     *
     * @param x coordinate
     * @param y coordinate
     */
    fun click(x: Int, y: Int) {
        view.perform(UiSystemActionType.CLICK) {
            checkBooleanAction("${UiSystemActionType.CLICK}(x=$x, y=$y)") { click(x, y) }
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
        view.perform(UiSystemActionType.DRAG) {
            checkBooleanAction(
                "${UiSystemActionType.DRAG}(startX=$startX, startY=$startY, endX=$endX, endY=$endY, steps=$steps)"
            ) {
                drag(startX, startY, endX, endY, steps)
            }
        }
    }

    /**
     * This method simply presses the power button if the screen is ON else
     * it does nothing if the screen is already OFF.
     */
    fun sleep() {
        view.perform(UiSystemActionType.SLEEP) {
            checkAction(UiSystemActionType.SLEEP) { sleep() }
        }
    }

    /**
     * This method simulates pressing the power button if the screen is OFF else
     * it does nothing if the screen is already ON.
     */
    fun wakeUp() {
        view.perform(UiSystemActionType.WAKE_UP) {
            checkAction(UiSystemActionType.WAKE_UP) { wakeUp() }
        }
    }

    private fun checkBooleanAction(methodName: String, action: () -> Boolean) {
        val result = action.invoke()
        if (!result) throw AssertionError("$methodName method in UiAutomator hasn't been performed")
    }

    private fun checkBooleanAction(methodName: UiOperationType, action: () -> Boolean) =
        checkBooleanAction(methodName.name, action)

    private fun checkAction(methodName: UiOperationType, action: () -> Unit) {
        try {
            action.invoke()
        } catch (exception: Exception) {
            throw AssertionError("$methodName method in UiAutomator hasn't been performed")
        }
    }

    enum class UiSystemActionType : UiOperationType {
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
