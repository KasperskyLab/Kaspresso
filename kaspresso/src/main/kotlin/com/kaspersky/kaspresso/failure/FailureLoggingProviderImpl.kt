package com.kaspersky.kaspresso.failure

import android.view.View
import androidx.test.espresso.PerformException
import com.kaspersky.kaspresso.internal.exceptions.ThrowableWithInteraction
import com.kaspersky.kaspresso.internal.extensions.espressoext.describe
import com.kaspersky.kaspresso.internal.extensions.other.getException
import com.kaspersky.kaspresso.internal.extensions.other.getStackTraceAsString
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.failure.describe.FailedAssertionDescriber
import io.reactivex.exceptions.ExtCompositeException
import junit.framework.AssertionFailedError
import org.hamcrest.Matcher

/**
 * The implementation of the [FailureLoggingProvider] interface.
 */
class FailureLoggingProviderImpl<Interaction>(
    private val logger: UiTestLogger,
    private val failedAssertionDescriber: FailedAssertionDescriber<Interaction>? = null
) : FailureLoggingProvider<Interaction> {

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
    override fun <T> withLoggingOnFailure(interaction: Interaction?, action: () -> T): T {
        return try {
            action.invoke()
        } catch (error: Throwable) {
            logStackTrace(interaction, error)

            throw when (error) {
                is ThrowableWithInteraction -> {
                    error.throwable
                }
                is ExtCompositeException -> {
                    error.exceptions.map { if (it is ThrowableWithInteraction) it.throwable else it }.getException()!!
                }
                else -> {
                    error
                }
            }
        }
    }

    /**
     * Logs the [error]'s stacktrace.
     *
     * @param error the error to get stacktrace from.
     */
    override fun logStackTrace(interaction: Interaction?, error: Throwable) {
        when (error) {
            is ExtCompositeException -> {
                logger.e(error.getStackTraceAsString())
                error.exceptions.forEachIndexed { i: Int, e: Throwable ->
                    logger.e("Composed exception ${i + 1} :")
                    if (e is ThrowableWithInteraction) {
                        if (e.throwable is AssertionError) {
                            failedAssertionDescriber?.getDescription(e.interaction as Interaction)
                                ?.let {
                                    logger.e(
                                        e.throwable.getStackTraceAsString()
                                            .replace(e.throwable.toString(), "${e.throwable.javaClass.name}: $it")
                                    )
                                }
                                ?: logger.e(e.throwable.getStackTraceAsString())
                        } else {
                            logger.e(e.throwable.getStackTraceAsString())
                        }
                    } else {
                        logger.e(e.getStackTraceAsString())
                    }
                }
            }
            else -> {
                if (interaction != null && error is AssertionError) {
                    failedAssertionDescriber?.getDescription(interaction)
                        ?.let {
                            logger.e(
                                error.getStackTraceAsString()
                                    .replace(error.toString(), "${error.javaClass.name}: $it")
                            )
                        }
                        ?: logger.e(error.getStackTraceAsString())
                } else {
                    logger.e(error.getStackTraceAsString())
                }
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