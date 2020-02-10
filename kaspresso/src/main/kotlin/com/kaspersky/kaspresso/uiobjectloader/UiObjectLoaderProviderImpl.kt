package com.kaspersky.kaspresso.uiobjectloader

import androidx.test.uiautomator.StaleObjectException
import com.kaspersky.components.kautomator.intercept.exception.UnfoundedUiObjectException
import com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction
import com.kaspersky.kaspresso.internal.extensions.other.isAllowed
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * The implementation of the [UiObjectLoaderProvider] interface.
 */
class UiObjectLoaderProviderImpl(
    private val logger: UiTestLogger
) : UiObjectLoaderProvider {

    companion object {
        private val OBJECT_ABSENCE_EXCEPTIONS: Set<Class<out Throwable>> =
            setOf(UnfoundedUiObjectException::class.java, StaleObjectException::class.java)
    }

    /**
     * Attempt to find [androidx.test.uiautomator.UiObject2] in case of [androidx.test.uiautomator.UiObject2] absence or stale
     */
    override fun <T> handleUiObjectAbsence(interaction: UiObjectInteraction, action: () -> T): T {
        return try {
            action.invoke()
        } catch (error: Throwable) {
            if (error.isAllowed(OBJECT_ABSENCE_EXCEPTIONS)) {
                logger.i("UiView loaded by selector=${interaction.selector} is obsolete. " +
                        "The next action is to attempt to reload UiView")
                interaction.tryToFindUiObject()
                return action.invoke()
            }
            throw error
        }
    }
}