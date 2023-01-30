package com.kaspersky.kaspresso.autoscroll

import androidx.test.uiautomator.UiScrollable
import androidx.test.uiautomator.UiSelector
import com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction
import com.kaspersky.kaspresso.internal.extensions.other.isAllowed
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.AutoScrollParams

/**
 * The implementation of the [AutoScrollProvider] interface for [UiObjectInteraction]
 */
class ObjectAutoScrollProviderImpl(
    private val logger: UiTestLogger,
    private val autoScrollParams: AutoScrollParams
) : AutoScrollProvider<UiObjectInteraction> {

    /**
     * Invokes the given [action] and calls [scroll] if it fails. Helps in cases when test fails because of the
     * need to scroll to interacted view.
     *
     * @param interaction the instance of [UiObjectInteraction] interface to perform actions and assertions.
     * @param action the actual action on the interacted view.
     *
     * @throws Throwable if the exception caught while invoking [action] is not allowed via [ALLOWED_EXCEPTIONS].
     * @return the result of [action] invocation.
     */
    override fun <T> withAutoScroll(interaction: UiObjectInteraction, action: () -> T): T {
        return try {
            action.invoke()
        } catch (error: Throwable) {
            if (error.isAllowed(autoScrollParams.allowedExceptions) && UiScrollable(UiSelector().scrollable(true)).exists()) {
                return scroll(interaction, action, error)
            }
            throw error
        }
    }

    /**
     * Performs the autoscrolling functionality. Performs scroll and re-invokes the given [action].
     *
     * @param interaction the instance of [UiObjectInteraction] interface to perform actions and assertions.
     * @param action the actual action on the interacted view.
     * @param cachedError the error to be thrown if autoscroll would not help.
     *
     * @throws cachedError if autoscroll action did not help.
     * @return the result of [action] invocation.
     */
    override fun <T> scroll(interaction: UiObjectInteraction, action: () -> T, cachedError: Throwable): T {
        /**
         *  Looks for a scrollable content
         */
        val scrollable = UiScrollable(UiSelector().scrollable(true))

        /**
         * Scrolls to the bottom and looks for the given view. Invokes the action if the view was found.
         */
        do {
            if (interaction.uiObject2 != null) {
                logger.i("UiObject autoscroll to the bottom successfully performed.")
                return action.invoke()
            } else {
                interaction.reFindUiObject()
            }
        } while (scrollable.exists() && scrollable.scrollForward())

        /**
         * Scrolls to the beginning and looks for the given view. Invokes the action if the view was found.
         */
        do {
            if (interaction.uiObject2 != null) {
                logger.i("UiObject autoscroll to the beginning successfully performed.")
                return action.invoke()
            } else {
                interaction.reFindUiObject()
            }
        } while (scrollable.exists() && scrollable.scrollBackward())

        logger.i("UiObject autoscroll did not help. Throwing exception.")
        throw cachedError
    }
}
