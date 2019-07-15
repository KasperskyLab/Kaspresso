package com.kaspersky.kaspresso.flakysafety

import android.support.test.InstrumentationRegistry
import android.support.test.uiautomator.By
import android.support.test.uiautomator.BySelector
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.Until
import android.widget.FrameLayout
import com.kaspersky.kaspresso.configurator.Configurator
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
 * @param isAndroidSystemHandling is attempt() method handling android system dialogs/windows.
 * If the param is true (by default the param is true) and the android system dialog was detected
 * then we try to remove detected dialog and rerun attempt() with
 * timeoutMs=Configurator.attemptsTimeoutMs and intervalMs=Configurator.attemptsIntervalMs
 * @param action an action that is attempted to be invoked.
 * @return [T] as it is a result of [action] invocation.
 */
fun <T> attempt(
    timeoutMs: Long = Configurator.attemptsTimeoutMs,
    intervalMs: Long = Configurator.attemptsIntervalMs,
    message: String? = null,
    logger: UiTestLogger = Configurator.logger,
    allowedExceptions: Set<Class<out Throwable>> = Configurator.allowedExceptionsForAttempt,
    isAndroidSystemHandling: Boolean = true,
    action: () -> T
) {
    if (intervalMs >= timeoutMs) {
        throw IllegalArgumentException("The interval of attempts should be longer than the timeout of all attempts")
    }

    var caughtAllowedException: Throwable
    val startTime = System.currentTimeMillis()

    do {
        try {
            action.invoke()
            return
        } catch (e: Throwable) {
            val isExceptionAllowed =
                allowedExceptions.find { it.isAssignableFrom(e.javaClass) } != null

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

    with(UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())) {
        if (isVisible(By.pkg("android").clazz(FrameLayout::class.java))) {
            logger.i("attempt() method. The android system dialog/window was detected. " +
                    "Kaspresso presses back and tries to rerun attempt() with timeoutMS=${Configurator.attemptsTimeoutMs} " +
                    "and intervalMs=${Configurator.attemptsIntervalMs}"
            )
            pressBack()
            attempt(
                timeoutMs = Configurator.attemptsTimeoutMs,
                intervalMs = Configurator.attemptsIntervalMs,
                message = message,
                logger = logger,
                allowedExceptions = allowedExceptions,
                isAndroidSystemHandling = isAndroidSystemHandling,
                action = action
            )
            return
        }
    }

    logger.e(
        "All attempts to interact for $timeoutMs ms totally failed " +
                "because of ${caughtAllowedException.javaClass.simpleName}"
    )

    logger.e(caughtAllowedException.getStackTraceAsString())

    failAttempt(message, caughtAllowedException)
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

/**
 * isVisible method for non-app's elements and with a waiting
 */
private fun UiDevice.isVisible(selector: BySelector, timeMs: Long = 1000): Boolean {
    wait(Until.findObject(selector), timeMs)
    return findObject(selector) != null
}