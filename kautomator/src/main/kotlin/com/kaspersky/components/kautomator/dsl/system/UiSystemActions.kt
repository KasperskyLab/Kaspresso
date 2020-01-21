package com.kaspersky.components.kautomator.dsl.system

import com.kaspersky.components.kautomator.intercepting.delegate.UiDeviceDelegate
import com.kaspersky.components.kautomator.intercepting.operation.UiOperationType

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
        view.perform(SystemActionType.OPEN_NOTIFICATION) {
            checkBooleanAction(SystemActionType.OPEN_NOTIFICATION) {
                openNotification()
            }
        }
    }

    /**
     * Opens the Quick Settings shade
     */
    fun openQuickSettings() {
        view.perform(SystemActionType.OPEN_QUICK_SETTINGS) {
            checkBooleanAction(SystemActionType.OPEN_QUICK_SETTINGS) { openQuickSettings() }
        }
    }

    /**
     * Simulates a short press on the DELETE key
     */
    fun pressDelete() {
        view.perform(SystemActionType.PRESS_DELETE) {
            checkBooleanAction(SystemActionType.PRESS_DELETE) { pressDelete() }
        }
    }

    /**
     * Simulates a short press on the ENTER key
     */
    fun pressEnter() {
        view.perform(SystemActionType.PRESS_ENTER) {
            checkBooleanAction(SystemActionType.PRESS_ENTER) { pressEnter() }
        }
    }

    /**
     * Simulates a short press on the HOME button
     */
    fun pressHome() {
        view.perform(SystemActionType.PRESS_HOME) {
            checkBooleanAction(SystemActionType.PRESS_HOME) { pressHome() }
        }
    }

    /**
     * Simulates a short press on the MENU button
     */
    fun pressMenu() {
        view.perform(SystemActionType.PRESS_MENU) {
            checkBooleanAction(SystemActionType.PRESS_MENU) { pressMenu() }
        }
    }

    /**
     * Simulates a short press on the Recent Apps button
     */
    fun pressRecentApps() {
        view.perform(SystemActionType.PRESS_RECENT_APPS) {
            checkBooleanAction(SystemActionType.PRESS_RECENT_APPS) { pressRecentApps() }
        }
    }

    /**
     * Simulates a short press on the SEARCH button
     */
    fun pressSearch() {
        view.perform(SystemActionType.PRESS_SEARCH) {
            checkBooleanAction(SystemActionType.PRESS_SEARCH) { pressSearch() }
        }
    }

    /**
     * Perform a click at arbitrary coordinates specified by the user (click by System)
     *
     * @param x coordinate
     * @param y coordinate
     */
    fun click(x: Int, y: Int) {
        view.perform(SystemActionType.CLICK) {
            checkBooleanAction("${SystemActionType.CLICK}(x=$x, y=$y)") { click(x, y) }
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
        view.perform(SystemActionType.DRAG) {
            checkBooleanAction(
                "${SystemActionType.DRAG}(startX=$startX, startY=$startY, endX=$endX, endY=$endY, steps=$steps)") {
                drag(startX, startY, endX, endY, steps)
            }
        }
    }

    /**
     * This method simply presses the power button if the screen is ON else
     * it does nothing if the screen is already OFF.
     */
    fun sleep() {
        view.perform(SystemActionType.SLEEP) {
            checkAction(SystemActionType.SLEEP) { sleep() }
        }
    }

    /**
     * This method simulates pressing the power button if the screen is OFF else
     * it does nothing if the screen is already ON.
     */
    fun wakeUp() {
        view.perform(SystemActionType.WAKE_UP) {
            checkAction(SystemActionType.WAKE_UP) { wakeUp() }
        }
    }

    private fun checkBooleanAction(methodName: String, action: () -> Boolean) {
        val result = action.invoke()
        if (!result) throw AssertionError("$methodName method in UiAutomator hasn't performed")
    }

    private fun checkBooleanAction(methodName: UiOperationType, action: () -> Boolean) =
        checkBooleanAction(methodName.name, action)

    private fun checkAction(methodName: UiOperationType, action: () -> Unit) {
        try {
            action.invoke()
        } catch (exception: Exception) {
            throw AssertionError("$methodName method in UiAutomator hasn't performed", exception)
        }
    }

    enum class SystemActionType : UiOperationType {
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