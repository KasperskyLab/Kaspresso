package com.kaspersky.kaspresso.failure

import android.view.View
import androidx.test.espresso.PerformException
import com.kaspersky.kaspresso.internal.extensions.espressoext.describe
import com.kaspersky.kaspresso.internal.extensions.other.getStackTraceAsString
import com.kaspersky.kaspresso.logger.UiTestLogger
import junit.framework.AssertionFailedError
import org.hamcrest.Matcher

interface FailureLoggingProvider {

    val logger: UiTestLogger

    fun <T> withLoggingOnFailure(action: () -> T): T {
        return try {
            action.invoke()
        } catch (error: Throwable) {
            logStackTrace(error)
            throw error
        }
    }

    fun logStackTrace(error: Throwable) {
        logger.e(error.getStackTraceAsString())
    }

    @Throws(Throwable::class)
    fun logDescriptionAndThrow(error: Throwable?, viewMatcher: Matcher<View>?) {
        logger.e(
            "Failed to interact with view matching: ${viewMatcher.describe()}" +
                    error?.let { " because of ${error.javaClass.simpleName}" }
        )

        error?.let { throw it.describedWith(viewMatcher) }
    }

    /**
     * Transforms an exception to more readable form.
     *
     * @param error the reason of failure.
     * @param viewMatcher a matcher, which corresponded to the view on which the error occurred.
     * @return transformed [error]
     */
    private fun Throwable.describedWith(viewMatcher: Matcher<View>?): Throwable {
        return when (this) {
            is PerformException -> {
                PerformException.Builder()
                    .from(this)
                    .apply { viewMatcher?.let { withViewDescription(it.toString()) } }
                    .build()
            }
            is AssertionError -> {
                AssertionFailedError(message).initCause(this)
            }
            else -> this
        }.apply { stackTrace = Thread.currentThread().stackTrace }
    }
}

fun <T> FailureLoggingProvider?.withLoggingOnFailureIfNotNull(action: () -> T): T =
    if (this != null) withLoggingOnFailure(action) else action.invoke()