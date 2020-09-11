package com.kaspersky.kaspresso.systemsafety

/**
 * An interface to provide system dialog safety functionality.
 */
interface SystemDialogSafetyProvider {

    /**
     * Invokes the given [action] and hides the system dialog if the invocation is failed and the system
     * dialog is actually shown via [suppressSystemDialogs] call.
     *
     * @param action the action to invoke.
     *
     * @return the result of [action]'s invocation.
     */
    fun <T> passSystemDialogs(action: () -> T): T
}
