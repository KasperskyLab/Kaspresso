package com.kaspersky.kaspresso.configurator

import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.PerformException
import com.kaspersky.kaspresso.logger.CachedLogger
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.logger.UiTestLoggerImpl

/**
 * Use this object only for extensions and nothing else!
 * In other cases use [Configurator]
 */
internal object ConfiguratorExt {

    var attemptsTimeoutMs: Long = Configurator.DEFAULT_ATTEMPTS_TIMEOUT_MS
    var attemptsIntervalMs: Long = Configurator.DEFAULT_ATTEMPTS_INTERVAL_MS
    var logger: UiTestLogger = UiTestLoggerImpl(Configurator.DEFAULT_INNER_LOGGER_TAG)
    var cachedLogger: CachedLogger = CachedLogger(logger)
    var allowedExceptionsForAttempt: Set<Class<out Throwable>> = setOf(
        PerformException::class.java,
        NoMatchingViewException::class.java,
        AssertionError::class.java
    )

}