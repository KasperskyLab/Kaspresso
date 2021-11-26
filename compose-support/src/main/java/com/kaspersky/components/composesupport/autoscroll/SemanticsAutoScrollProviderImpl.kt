package com.kaspersky.components.composesupport.autoscroll

import androidx.compose.ui.test.performScrollTo
import com.kaspersky.kaspresso.autoscroll.AutoScrollProvider
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.AutoScrollParams
import io.github.kakaocup.compose.intercept.interaction.ComposeInteraction
import io.reactivex.exceptions.ExtCompositeException

class SemanticsAutoScrollProviderImpl(
    private val logger: UiTestLogger,
    private val autoScrollParams: AutoScrollParams
) : AutoScrollProvider<ComposeInteraction> {

    /**
     * Invokes the given [action] and calls [scroll] if it fails. Helps in cases when test fails because of the
     * need to scroll to interacted view.
     *
     * @param interaction the instance of [ComposeInteraction] interface to perform actions and assertions.
     * @param action the actual action on the interacted view.
     *
     * @throws Throwable if the exception caught while invoking [action] is not allowed via [ALLOWED_EXCEPTIONS].
     * @return the result of [action] invocation.
     */
    override fun <T> withAutoScroll(interaction: ComposeInteraction, action: () -> T): T {
        return try {
            action.invoke()
        } catch (error: Throwable) {
            if (error.isAllowed(autoScrollParams.allowedExceptions)) {
                return scroll(interaction, action, error)
            }
            throw error
        }
    }

    // temp copy-paste of com.kaspersky.kaspresso.internal.extensions.other.isAllowed
    // probably, we will create an a separate "utils" module to avoid providing of such utils classes to users
    private fun <T : Throwable> T.isAllowed(allowed: Set<Class<out Throwable>>): Boolean {
        return when (this) {
            is ExtCompositeException -> {
                exceptions.find { e: Throwable ->
                    allowed.find { it.isAssignableFrom(e.javaClass) } != null
                } != null
            }
            else -> {
                allowed.find { it.isAssignableFrom(javaClass) } != null
            }
        }
    }

    /**
     * Performs the autoscrolling functionality. Performs scroll and re-invokes the given [action].
     *
     * @param interaction the instance of [ComposeInteraction] interface to perform actions and assertions.
     * @param action the actual action on the interacted view.
     * @param cachedError the error to be thrown if autoscroll would not help.
     *
     * @throws cachedError if autoscroll action did not help.
     * @return the result of [action] invocation.
     */
    override fun <T> scroll(interaction: ComposeInteraction, action: () -> T, cachedError: Throwable): T {
        return try {
            interaction.semanticsNodeInteraction.performScrollTo()
            logger.i("SemanticsNodeInteraction autoscroll successfully performed.")
            action.invoke()
        } catch (error: Throwable) {
            logger.i("SemanticsNodeInteraction autoscroll did not help. Throwing exception.")
            throw cachedError
        }
    }
}
