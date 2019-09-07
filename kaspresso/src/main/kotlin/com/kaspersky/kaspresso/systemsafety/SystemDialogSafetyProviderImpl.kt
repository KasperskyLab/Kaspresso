package com.kaspersky.kaspresso.systemsafety

import android.widget.FrameLayout
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import com.kaspersky.kaspresso.internal.extensions.other.isAllowed
import com.kaspersky.kaspresso.logger.UiTestLogger

class SystemDialogSafetyProviderImpl(
    private val params: SystemDialogSafetyParams,
    private val logger: UiTestLogger
) : SystemDialogSafetyProvider {

    @Throws(Throwable::class)
    override fun <T> passSystemDialogs(action: () -> T): T {
        return try {
            action.invoke()
        } catch (exception: Exception) {
            val isExceptionIntercepted = exception.isAllowed(params.allowedExceptions)
            if (isExceptionIntercepted && isAndroidSystemDetectedAndRemoved()) {
                return action.invoke()
            }
            throw exception
        }
    }

    /**
     * Check android system dialogs/windows are overlaying the app.
     * If system dialog/window has been detected then try to remove it by back button pressing.
     */
    private fun isAndroidSystemDetectedAndRemoved(): Boolean {
        with(UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())) {
            if (isVisible(By.pkg("android").clazz(FrameLayout::class.java))) {
                logger.i(
                    "The android system dialog/window was detected. " +
                            "Kaspresso presses back to try remove detected dialog/window"
                )
                pressBack()
                return true
            }
        }
        return false
    }

    /**
     * isVisible method for non-app's elements and with a waiting
     */
    private fun UiDevice.isVisible(selector: BySelector, timeMs: Long = 1000): Boolean {
        wait(Until.findObject(selector), timeMs)
        return findObject(selector) != null
    }
}