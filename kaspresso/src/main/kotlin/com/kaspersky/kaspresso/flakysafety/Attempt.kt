package com.kaspersky.kaspresso.flakysafety

import android.support.test.InstrumentationRegistry
import android.support.test.uiautomator.By
import android.support.test.uiautomator.BySelector
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.Until
import android.widget.FrameLayout
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.exceptions.AndroidSystemOverlayException
import com.kaspersky.kaspresso.exceptions.KaspressoAssertionError
import com.kaspersky.kaspresso.extensions.other.getStackTraceAsString
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * Makes several attempts to invoke an action.
 *
 * @param timeoutMs a timeout for all attempts in milliseconds.
 * @param intervalMs an interval between attempts in milliseconds.
 * @param message a message of an AssertionError if it's set to anything but null.
 * @param logger a logger to log errors.
 * @param allowedExceptions exceptions that doesn't stop attempts.
 * @param action an action that is attempted to be invoked.
 * @return [T] as it is a result of [action] invocation.
 */
fun <T> attempt(
    timeoutMs: Long = Configurator.attemptsTimeoutMs,
    intervalMs: Long = Configurator.attemptsIntervalMs,
    message: String? = null,
    logger: UiTestLogger = Configurator.logger,
    allowedExceptions: Set<Class<out Throwable>> = Configurator.allowedExceptionsForAttempt,
    action: () -> T
) {
    if (intervalMs >= timeoutMs) {
        throw IllegalArgumentException("The interval of attempts should be longer than the timeout of all attempts")
    }

    var caughtAllowedException: Throwable
    val startTime = System.currentTimeMillis()
    var needCheckAndroidSystemFirstTime = true

    do {
        try {
            action.invoke()
            return
        } catch (e: Throwable) {
            // check android system dialog/window only first and one time because
            // usually the fact of overlaying is discovered at the beginning of work
            if (needCheckAndroidSystemFirstTime) {
                needCheckAndroidSystemFirstTime = false
                if (isAndroidSystemDetectedAndRemoved(logger)) {
                    caughtAllowedException = AndroidSystemOverlayException("Android system dialog/window has overlaid the application")
                    continue
                }
            }

            val isExceptionAllowed = allowedExceptions.find { it.isAssignableFrom(e.javaClass) } != null
            when {
                isExceptionAllowed -> {
                    Thread.sleep(intervalMs)
                    caughtAllowedException = e
                }
                else -> {
                    throw e
                }
            }
        }
    } while (System.currentTimeMillis() - startTime <= timeoutMs)

    logger.e(
        "All attempts to interact for $timeoutMs ms totally failed " +
                "because of ${caughtAllowedException.javaClass.simpleName}"
    )

    logger.e(caughtAllowedException.getStackTraceAsString())

    failAttempt(message, caughtAllowedException)
}

/**
 * Check android system dialogs/windows are overlaying the app.
 * If system dialog/window has been detected then try to remove it by back button pressing.
 */
private fun isAndroidSystemDetectedAndRemoved(logger: UiTestLogger): Boolean {
    with(UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())) {
        if (isVisible(By.pkg("android").clazz(FrameLayout::class.java))) {
            logger.i("The android system dialog/window was detected. " +
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

/**
 *  Throws [KaspressoAssertionError] if message is specified, or [caughtAllowedException] otherwise.
 */
private fun failAttempt(
    message: String?,
    caughtAllowedException: Throwable
): Nothing {
    message ?: throw caughtAllowedException
    throw KaspressoAssertionError(message, caughtAllowedException)
}