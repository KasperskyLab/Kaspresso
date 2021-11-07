package com.kaspersky.kaspresso.elementloader

import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * The implementation of the [ElementLoaderProvider] interface
 */
class ElementLoaderProviderImpl(
    private val logger: UiTestLogger,
) : ElementLoaderProvider {

    /**
     * Invokes the given [action] and calls [elementLoader] if it fails. Helps in cases when test fails because
     * the element is outdated and must be reloaded using its selectors/matchers.
     *
     * @param elementLoader the lambda to reload the element.
     * @param action the actual action on the interacted view.
     *
     * @return the result of [action] invocation.
     */
    override fun <ActionType> passAction(
        elementLoader: () -> Unit,
        action: () -> ActionType
    ): ActionType {
        return try {
            action.invoke()
        } catch (error: Throwable) {
            logger.i("Reloading of the element is started")
            elementLoader.invoke()
            logger.i("Reloading of the element is finished")
            logger.i("Repeat action again with the reloaded element")
            action.invoke()
        }
    }
}
