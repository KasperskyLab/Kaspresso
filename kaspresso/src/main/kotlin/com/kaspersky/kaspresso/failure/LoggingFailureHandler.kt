package com.kaspersky.kaspresso.failure

import android.view.View
import androidx.test.espresso.FailureHandler
import androidx.test.espresso.ViewInteraction
import com.kaspersky.kaspresso.logger.UiTestLogger
import org.hamcrest.Matcher

/**
 * The implementation of the [FailureHandler] interface that logs rich description of failure.
 */
class LoggingFailureHandler(
    logger: UiTestLogger
) : FailureHandler,
    FailureLoggingProvider<ViewInteraction> by FailureLoggingProviderImpl(logger) {

    /**
     * Calls [logDescriptionAndThrow] on each failure.
     */
    @Throws(Throwable::class)
    override fun handle(error: Throwable?, viewMatcher: Matcher<View>?) {
        logDescriptionAndThrow(error, viewMatcher)
    }
}