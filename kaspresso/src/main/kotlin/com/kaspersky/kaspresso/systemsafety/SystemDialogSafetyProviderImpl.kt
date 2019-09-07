package com.kaspersky.kaspresso.systemsafety

import android.widget.FrameLayout
import androidx.test.uiautomator.*
import com.kaspersky.kaspresso.logger.UiTestLogger

class SystemDialogSafetyProviderImpl(
    private val logger: UiTestLogger,
    private val uiDevice: UiDevice
) : SystemDialogSafetyProvider {

    private val attemptsToSuppress: List<(UiDevice) -> Unit> = listOf(
        { uiDevice -> uiDevice.findObject(UiSelector().resourceId("android:id/button1")).click() },
        { uiDevice -> uiDevice.pressBack() }
    )

    override fun <T> passSystemDialogs(action: () -> T): T {
        return try {
            action.invoke()
        } catch (error: Throwable) {
            if (isAndroidSystemDetected()) {
                return suppressSystemDialogs(error, action)
            }
            throw error
        }
    }

    private fun <R> suppressSystemDialogs(firstError: Throwable, action: () -> R): R {
        logger.i("The suppressing of SystemDialogs starts")
        var cachedError: Throwable = firstError
        attemptsToSuppress.forEachIndexed { index, attemptToSuppress ->
            try {
                logger.i("The suppressing of SystemDialogs on the try #$index starts")
                attemptToSuppress.invoke(uiDevice)
                val result = action.invoke()
                logger.i("The suppressing of SystemDialogs succeeds on the try #$index")
                return result
            } catch (error: Throwable) {
                logger.i("The try #$index of the suppressing of SystemDialogs failed")
                cachedError = error
                if (!isAndroidSystemDetected()) {
                    logger.i(
                        "The try #$index of the suppressing of SystemDialogs failed. " +
                                "The reason is the error is not allowed or " +
                                "android system dialog is suppressed but the error is existing"
                    )
                    throw cachedError
                }
            }
        }
        logger.i("The suppressing of SystemDialogs totally failed")
        throw cachedError
    }

    /**
     * Check error is allowed and android system dialogs/windows are overlaying the app.
     */
    private fun isAndroidSystemDetected(): Boolean {
        with(uiDevice) {
            if (isVisible(By.pkg("android").clazz(FrameLayout::class.java))) {
                logger.i("The android system dialog/window was detected")
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