package com.kaspersky.kaspresso.objectloader

import androidx.test.uiautomator.StaleObjectException
import com.kaspersky.components.uiautomatordsl.intercepting.exception.UnfoundedUiObjectException
import com.kaspersky.components.uiautomatordsl.intercepting.interaction.UiObjectInteraction
import com.kaspersky.kaspresso.internal.extensions.other.isAllowed
import com.kaspersky.kaspresso.logger.UiTestLogger

class ObjectLoaderProviderImpl(
    private val logger: UiTestLogger
) : ObjectLoaderProvider {

    companion object {
        private val OBJECT_ABSENCE_EXCEPTIONS: Set<Class<out Throwable>> =
            setOf(UnfoundedUiObjectException::class.java, StaleObjectException::class.java)
    }

    override fun <T> handleObjectAbsence(interaction: UiObjectInteraction, action: () -> T): T {
        return try {
            action.invoke()
        } catch (error: Throwable) {
            if (error.isAllowed(OBJECT_ABSENCE_EXCEPTIONS)) {
                logger.i("UiView loaded by selector=${interaction.selector} is obsolete." +
                        "Attempt to reload UiView")
                interaction.tryToFindUiObject()
                return action.invoke()
            }
            throw error
        }
    }
}