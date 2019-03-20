package com.kaspersky.kaspresso.interceptors.impl.logging

import android.view.View
import android.support.test.espresso.PerformException
import com.kaspersky.kaspresso.espressoext.matchers.describe
import com.kaspersky.kaspresso.interceptors.FailureInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import junit.framework.AssertionFailedError
import org.hamcrest.Matcher

class LoggingFailureInterceptor(
    private val uiTestLogger: UiTestLogger
) : FailureInterceptor {

    override fun interceptAndThrow(error: Throwable, viewMatcher: Matcher<View>) {

        uiTestLogger.e(
            "Failed to interact with view matching: ${viewMatcher.describe()} " +
                    "because of ${error.javaClass.simpleName}"
        )

        throw getSelfDescribedError(error, viewMatcher)
    }

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