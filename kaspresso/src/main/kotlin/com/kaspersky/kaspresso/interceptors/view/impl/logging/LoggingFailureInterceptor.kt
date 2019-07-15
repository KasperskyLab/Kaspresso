package com.kaspersky.kaspresso.interceptors.view.impl.logging

import android.support.test.espresso.PerformException
import android.view.View
import com.kaspersky.kaspresso.extensions.espressoext.describe
import com.kaspersky.kaspresso.interceptors.view.FailureInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import junit.framework.AssertionFailedError
import org.hamcrest.Matcher

/**
 * An implementation of [FailureInterceptor] that logs rich description of failure.
 */
class LoggingFailureInterceptor(
    private val logger: UiTestLogger
) : FailureInterceptor {

    /**
     * Writes info to [logger] and throws an exception further.
     *
     * @param error the reason of failure.
     * @param viewMatcher a matcher, which corresponded to the view on which the error occurred.
     */
    override fun interceptAndThrow(error: Throwable, viewMatcher: Matcher<View>) {
        logger.e(
            "Failed to interact with view matching: ${viewMatcher.describe()} " +
                    "because of ${error.javaClass.simpleName}"
        )

        throw getSelfDescribedError(error, viewMatcher)
    }

    /**
     * Transforms an exception to more readable form.
     *
     * @param error the reason of failure.
     * @param viewMatcher a matcher, which corresponded to the view on which the error occurred.
     * @return transformed [error]
     */
    private fun getSelfDescribedError(
        error: Throwable,
        viewMatcher: Matcher<View>
    ): Throwable {
        var error = error

        if (error is PerformException) {
            val sb = StringBuilder()

            sb.append(viewMatcher.toString())

            error = PerformException.Builder()
                .from(error)
                .withViewDescription(sb.toString())
                .build()
        }

        if (error is AssertionError) {
            val assertionFailedError = AssertionFailedError(error.message)
            assertionFailedError.initCause(error)

            error = assertionFailedError
        }

        error.stackTrace = Thread.currentThread().stackTrace

        return error
    }
}