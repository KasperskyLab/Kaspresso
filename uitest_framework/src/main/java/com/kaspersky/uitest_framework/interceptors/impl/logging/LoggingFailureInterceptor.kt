package com.kaspersky.uitest_framework.interceptors.impl.logging

import android.view.View
import android.support.test.espresso.PerformException
import com.kaspersky.uitest_framework.espressoext.matchers.describe
import com.kaspersky.uitest_framework.interceptors.FailureInterceptor
import com.kaspersky.uitest_framework.logger.UiTestLogger
import junit.framework.AssertionFailedError
import org.hamcrest.Matcher

/**
 * An implementation of [FailureInterceptor] that logs rich description of failure.
 */
class LoggingFailureInterceptor(
    private val uiTestLogger: UiTestLogger
) : FailureInterceptor {

    /**
     * Writes info to [uiTestLogger] and throws an exception further.
     *
     * @param error the reason of failure.
     * @param viewMatcher a matcher, which corresponded to the view on which the error occurred.
     */
    override fun interceptAndThrow(error: Throwable, viewMatcher: Matcher<View>) {

        uiTestLogger.e(
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
