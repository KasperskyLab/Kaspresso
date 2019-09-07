package com.kaspersky.kaspresso.systemsafety

import android.widget.FrameLayout
import androidx.test.uiautomator.*
import com.kaspersky.kaspresso.internal.extensions.other.isAllowed
import com.kaspersky.kaspresso.logger.UiTestLogger
import io.reactivex.exceptions.CompositeException

class SystemDialogSafetyProviderImpl(
    private val params: SystemDialogSafetyParams,
    private val logger: UiTestLogger,
    private val uiDevice: UiDevice
) : SystemDialogSafetyProvider {

    private val attemptsToSuppress: List<(UiDevice) -> Unit> = listOf(
        { uiDevice -> uiDevice.pressBack() },
        { uiDevice -> uiDevice.findObject(UiSelector().resourceId("android:id/button1")).click() }
    )

    override fun <R> passSystemDialogs(action: () -> R): R {
        return try {
            action.invoke()
        } catch (error: Throwable) {
            if (isErrorAllowedAndAndroidSystemDetected(error)) {
                return suppressSystemDialogs(action)
            }
            throw error
        }
    }

    private fun <R> suppressSystemDialogs(action: () -> R): R {
        logger.i("The suppressing of SystemDialogs starts")
        val cachedErrors: MutableList<Throwable> = mutableListOf()
        attemptsToSuppress.forEach {
            try {
                it.invoke(uiDevice)
                val result = action.invoke()
                logger.i("The suppressing of SystemDialogs succeeds")
                return result
            } catch (error: Throwable) {
                logger.i("One part of the suppressing of SystemDialogs failed")
                cachedErrors += error
                if (!isErrorAllowedAndAndroidSystemDetected(error)) {
                    logger.i(
                        "The suppressing of SystemDialogs failed. " +
                                "The reason is the error is not allowed or " +
                                "android system dialog is suppressed but the error is existing"
                    )
                    throw CompositeException(cachedErrors)
                }
            }
        }
        logger.i("The suppressing of SystemDialogs totally failed")
        throw CompositeException(cachedErrors)
    }

    /**
     * Check error is allowed and android system dialogs/windows are overlaying the app.
     */
    private fun isErrorAllowedAndAndroidSystemDetected(error: Throwable): Boolean {
        if (!error.isAllowed(params.allowedExceptions)) {
            return false
        }
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