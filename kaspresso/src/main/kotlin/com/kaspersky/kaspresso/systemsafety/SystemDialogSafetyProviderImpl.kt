package com.kaspersky.kaspresso.systemsafety

import android.widget.FrameLayout
import androidx.test.uiautomator.By
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.instrumental.InstrumentalDependencyProvider
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.SystemDialogsSafetyParams
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

/**
 * The implementation of the [SystemDialogSafetyProvider] interface.
 */
class SystemDialogSafetyProviderImpl(
    private val logger: UiTestLogger,
    private val instrumentalDependencyProvider: InstrumentalDependencyProvider,
    private val adbServer: AdbServer,
    private val systemDialogsSafetyParams: SystemDialogsSafetyParams
) : SystemDialogSafetyProvider {

    companion object {
        private const val DEFAULT_TIMEOUT: Long = 2000
    }

    private val uiDevice: UiDevice
        get() = instrumentalDependencyProvider.uiDevice

    private val attemptsToSuppress: List<(UiDevice, AdbServer) -> Unit> = listOf(
        { _, adbServer ->
            adbServer.performShell("input keyevent KEYCODE_BACK")
            adbServer.performShell("input keyevent KEYCODE_ENTER")
            adbServer.performShell("input keyevent KEYCODE_ENTER")
        },
        { uiDevice, _ -> uiDevice.wait(Until.findObject(By.res("android:id/button1")), DEFAULT_TIMEOUT).click() },
        { uiDevice, _ -> uiDevice.wait(Until.findObject(By.res("android:id/closeButton")), DEFAULT_TIMEOUT).click() },
        { uiDevice, _ -> uiDevice.wait(Until.findObject(By.res("com.android.internal:id/aerr_close")), DEFAULT_TIMEOUT).click() },
        { uiDevice, _ -> uiDevice.pressBack() }
    )

    /**
     * Invokes the given [action] and hides the system dialog if the invocation is failed and the system
     * dialog is actually shown via [suppressSystemDialogs] call.
     *
     * @param action the action to invoke.
     *
     * @return the result of [action]'s invocation.
     *
     * @throws Throwable if caught while [action] invocation error is not allowed
     * or if[suppressSystemDialogs] throws an exception.
     */
    @Throws(Throwable::class)
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

    /**
     * Attempts to hide the system dialog and re-invokes the given [action].
     */
    @Throws(Throwable::class)
    private fun <R> suppressSystemDialogs(firstError: Throwable, action: () -> R): R {
        logger.i("The suppressing of SystemDialogs starts")
        var cachedError: Throwable = firstError

        attemptsToSuppress.forEachIndexed { index, attemptToSuppress ->
            try {
                logger.i("The suppressing of SystemDialogs on the try #$index starts")

                attemptToSuppress.invoke(uiDevice, adbServer)
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
     * Checks if error is allowed and android system dialogs/windows are overlaying the app.
     * Aware to use By.pkg with String, cause it will cause to use Pattern.quote in internal code,
     * internal use Pattern.match() method, so we need regex that will match full string, not part.
     */
    private fun isAndroidSystemDetected(): Boolean {
        with(uiDevice) {
            var isSystemDialogVisible = isVisible(By.pkg(Pattern.compile("\\S*google.android\\S*")).clazz(FrameLayout::class.java))

            if (systemDialogsSafetyParams.shouldIgnoreKeyboard) {
                val isKeyboardVisible = isVisible(By.pkg(Pattern.compile("\\S*google.android.inputmethod\\S*")).clazz(FrameLayout::class.java))
                isSystemDialogVisible = isSystemDialogVisible && !isKeyboardVisible
            }

            if (isSystemDialogVisible) {
                logger.i("The android system dialog/window was detected")
                return true
            }
        }
        return false
    }

    /**
     * The "isVisible" method with waiting for non-app's elements.
     */
    private fun UiDevice.isVisible(
        selector: BySelector,
        timeMs: Long = TimeUnit.SECONDS.toMillis(1)
    ): Boolean {
        wait(Until.findObject(selector), timeMs)
        return findObject(selector) != null
    }
}
