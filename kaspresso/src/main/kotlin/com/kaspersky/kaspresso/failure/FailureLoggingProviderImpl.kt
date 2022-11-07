package com.kaspersky.kaspresso.failure

import android.util.Log
import android.view.View
import androidx.test.espresso.PerformException
import com.kaspersky.kaspresso.internal.extensions.espressoext.describe
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
        logger.e(Log.getStackTraceString(error))

        if (error is ExtCompositeException) {
            error.exceptions.forEachIndexed { i: Int, e: Throwable ->
                logger.e("Composed exception ${i + 1} :")
                logger.e(Log.getStackTraceString(e))
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
        return when {
            this is PerformException -> {
                PerformException.Builder()
                    .from(this)
                    .apply { viewMatcher?.let { withViewDescription(it.toString()) } }
                    .build()
            }
            this is AssertionError -> {
                AssertionFailedError(message).initCause(this)
            }
            isWebViewException(this) -> {
                val message = StringBuilder("Failed to interact with web view! Usually it means that desired element is not found or JavaScript is disabled in web view")
                viewMatcher?.let { message.append("\nView description: ${it.describe()}") }
                RuntimeException(message.toString())
            }
            else -> this
        }.apply { stackTrace = Thread.currentThread().stackTrace }
    }

    private fun isWebViewException(throwable: Throwable): Boolean {
        return throwable is RuntimeException && throwable.message?.contains("atom evaluation returned null", ignoreCase = true) ?: false
    }
}
