package com.kaspersky.kaspresso.idlewaiting

/**
 * The interface to provide the functionality
 *     to handle problems caused by the specific of UiAutomator work with AccessibilityServiceManager Idling
 */
interface WaitForIdleProvider {

    /**
     * Invokes the given [action] and tries to resolve potential AccessibilityServiceManager Idling problem
     *
     * @param action the action to invoke.
     *
     * @return the [action] invocation result.
     */
    fun <T> handleLongIdlingWait(action: () -> T): T
}