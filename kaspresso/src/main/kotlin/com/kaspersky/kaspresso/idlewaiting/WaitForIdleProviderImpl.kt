package com.kaspersky.kaspresso.idlewaiting

import androidx.test.uiautomator.Configurator
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.WaitForIdleParams
import java.util.Timer
import kotlin.concurrent.schedule

/**
 * The implementation of the [WaitForIdleProvider] interface.
 *
 * The problem essence and an potential solve algorithm:
 * Please, have a look at `handleLongIdlingWait` and its `action` parameter.
 * At the beginning of this method WaitForIdleProviderImpl starts Timer. When timer went off WaitForIdleProviderImpl checks `action` passed or not.
 * If `action` has not been completed then `action` is in progress yet during `waitForIdleParams.timeoutMs`. So, something went wrong.
 * The most possible reason is spam from AccessibilityServiceManager (events are occurring every 100 ms, for example).
 * The problem essence: In UiAutomator, every action (check and perform) waits for the idle state before its execution.
 * The idle state is a time period when AccessibilityServiceManager doesn't receive any action from any application or system for 500 ms
 *     UiAutomator waits for such state before execution of concrete check or perform during 10 seconds.
 * If the idle state didn't happen then UiAutomator throws an exception.
 * That's why WaitForIdleProviderImpl waits for the result of `action` during `waitForIdleParams.timeoutMs` that is less than 10 seconds (5 seconds, by default)
 * After the mentioned timeout, WaitForIdleProviderImpl resets UiAutomator's `Configurator.waitForIdleTimeout` to zero and tries to repeat `action`.
 * After all of this, WaitForIdleProviderImpl restores cached `configurator.waitForIdleTimeout`.
 */
class WaitForIdleProviderImpl(
    private val configurator: Configurator,
    private val waitForIdleParams: WaitForIdleParams,
    private val logger: UiTestLogger
) : WaitForIdleProvider {

    companion object {
        private const val ZERO_IDLE_TIME: Long = 0
    }

    private val defaultWaitForIdleTimeout = configurator.waitForIdleTimeout

    override fun <T> handleLongIdlingWait(action: () -> T): T {
        var passed = false
        Timer().schedule(waitForIdleParams.timeoutMs) {
            if (!passed) {
                logger.i(
                    "The potential problem caused UiAutomator Idling specific was detected." +
                            "We try to struggle this problem resetting configurator.waitForIdleTimeout."
                )
                configurator.waitForIdleTimeout = ZERO_IDLE_TIME
            }
        }

        return try {
            action.invoke()
        } catch (error: Throwable) {
            if (passed) throw error
            action.invoke()
        }.also {
            passed = true
            configurator.waitForIdleTimeout = defaultWaitForIdleTimeout
            logger.i("Restoring of configurator.waitForIdleTimeout to default value.")
        }
    }
}