package com.kaspersky.kaspresso.failure

import android.support.test.espresso.PerformException
import android.view.View
import com.kaspersky.kaspresso.internal.extensions.espressoext.describe
import com.kaspersky.kaspresso.internal.extensions.other.getStackTraceAsString
import com.kaspersky.kaspresso.logger.UiTestLogger
import io.reactivex.exceptions.ExtCompositeException
import junit.framework.AssertionFailedError
import org.hamcrest.Matcher

/**
 * The implementation of the [FailureLoggingProvider] interface.
 */
class FailureLoggingProviderImpl(
    private val logger: UiTestLogger
) : FailureLoggingProvider {

    /**
     * Invokes the given [action] and logs if it fails.
     *
     * @param action the action to invoke.
     *
     * @return the result of the [action] invocation.
     *
     * @throws Throwable if the [action] invocation failed.
     */
    @Throws(Throwable::class)
    override fun <T> withLoggingOnFailure(action: () -> T): T {
        return try {
            action.invoke()
        } catch (error: Throwable) {
            logStackTrace(error)
            throw error
        }
    }

    /**
     * Logs the [error]'s stacktrace.
     *
     * @param error the error to get stacktrace from.
     */
    override fun logStackTrace(error: Throwable) {
        logger.e(error.getStackTraceAsString())

        if (error is ExtCompositeException) {
            error.exceptions.forEachIndexed { i: Int, e: Throwable ->
                logger.e("Composed exception ${i + 1} :")
                logger.e(e.getStackTraceAsString())
            }
        }
    }

    /**
     * Logs the [error] description got by [viewMatcher] and throws the [error].
     *
     * @param error the error to be described.
     * @param viewMatcher the view matcher to get description from.
     *
     * @throws Throwable the given [error].
     */
    @Throws(Throwable::class)
    override fun logDescriptionAndThrow(error: Throwable?, viewMatcher: Matcher<View>?) {
        logger.e(
            "Failed to interact with view matching: ${viewMatcher.describe()}" +
                    error?.let { " because of ${error.javaClass.simpleName}" }
        )

        error?.let { throw it.describedWith(viewMatcher) }
    }

    /**
     * Transforms the given exception to more readable form.
     *
     * @param viewMatcher a matcher, which corresponded to the view on which the error occurred.
     *
     * @return transformed [error].
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