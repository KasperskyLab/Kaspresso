package com.kaspersky.kaspresso.failure.describe

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertionsAndMatcherProvider
import com.kaspersky.kaspresso.failure.FailureLoggingParams
import com.kaspersky.kaspresso.internal.extensions.espressoext.describe

class FailedViewAssertionDescriber(
    private val failureLoggingParams: FailureLoggingParams,
    private val assertionCache: AssertionCache
) : ViewAssertionsAndMatcherProvider(),
    FailedAssertionDescriber<ViewInteraction> {

    override fun getDescription(interaction: ViewInteraction): String {
        val actualValue: String? = with(failureLoggingParams) {
            with(assertionCache) {
                when (cachedViewAssertion) {
                    MATCHES_VIEW_ASSERTION -> {
                        if (cachedViewMatcher == null) {
                            null
                        } else {
                            viewMatchersMap[cachedViewMatcher!!.javaClass]?.invoke().apply { interaction.perform(this) }
                                ?.value
                        }
                    }
                    else -> {
                        viewAssertionsMap[cachedViewAssertion]?.invoke(interaction)
                    }
                }
            }
        }

        return "assertion on selected view failed\n" +
                "Expected: ${assertionCache.cachedViewMatcher?.describe() ?: "[UNDEFINED]"}\n" +
                "Got: ${actualValue ?: "[UNDEFINED]"}"
    }
}