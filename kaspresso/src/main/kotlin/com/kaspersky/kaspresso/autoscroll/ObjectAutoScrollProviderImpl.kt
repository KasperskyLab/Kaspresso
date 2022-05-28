package com.kaspersky.kaspresso.autoscroll

import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.UiObjectNotFoundException
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
     * Performs the autoscrolling functionality. Performs scroll and re-invokes the given [action] for every
     * single scrollable on the screen, starting from the top and going through the nested scrollables.
     *
     * @param interaction the instance of [UiObjectInteraction] interface to perform actions and assertions.
     * @param action the actual action on the interacted view.
     * @param cachedError the error to be thrown if autoscroll would not help.
     *
     * @throws cachedError if autoscroll action did not help.
     * @return the result of [action] invocation.
     */
    override fun <T> scroll(interaction: UiObjectInteraction, action: () -> T, cachedError: Throwable): T {
        try {
            var index = 0
            do {
                /**
                 *  Looks for the next scrollable content. scrollable = null if no more found
                 */
                val uiSelector = UiSelector().scrollable(true).instance(index)
                val scrollable = UiScrollable(uiSelector).apply { setScrollingOrientation() }

                /**
                 * Scrolls to the bottom and looks for the given view. Invokes the action if the view was found.
                 */
                var successMessage = "UiObject autoscroll to the forwards successfully performed."
                var uiObject = interaction.findUiObjectWhile(successMessage) { scrollable.scrollForward() }
                if (uiObject != null) {
                    return action.invoke()
                }

                /**
                 * Scrolls to the beginning and looks for the given view. Invokes the action if the view was found.
                 */
                successMessage = "UiObject autoscroll to backwards successfully performed."
                uiObject = interaction.findUiObjectWhile(successMessage) { scrollable.scrollBackward() }
                if (uiObject != null) {
                    return action.invoke()
                }

                index += 1

            } while (true)

        } catch (e: UiObjectNotFoundException) {
            // thrown if scrollable == null -> no more scrollables found
        }

        logger.i("UiObject autoscroll did not help. Throwing exception.")
        throw cachedError
    }

    private fun UiObjectInteraction.findUiObjectWhile(
        successMessage: String,
        action: () -> Boolean,
    ): UiObject2? {
        do {
            if (uiObject2 != null) {
                logger.i(successMessage)
                return uiObject2
            } else {
                reFindUiObject()
            }
        } while (action())
        return null
    }

    private fun UiScrollable.setScrollingOrientation() {
        if (className.equals("android.widget.HorizontalScrollView")) {
            setAsHorizontalList()
        } else {
            setAsVerticalList()
        }
    }
}
