package com.kaspersky.kaspresso.elementloader

/**
 * The interface to provide element loader functionality.
 */
interface ElementLoaderProvider {

    /**
     * Invokes the given [action] and calls [elementLoader] if it fails. Helps in cases when test fails because
     * the element is outdated and must be reloaded using its selectors/matchers.
     *
     * @param elementLoader the lambda to reload the element.
     * @param action the actual action on the interacted view.
     *
     * @return the result of [action] invocation.
     */
    fun <ActionType> passAction(
        elementLoader: () -> Unit,
        action: () -> ActionType
    ): ActionType
}
