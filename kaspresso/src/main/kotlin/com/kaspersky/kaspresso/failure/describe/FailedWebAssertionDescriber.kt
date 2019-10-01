package com.kaspersky.kaspresso.failure.describe

import androidx.test.espresso.web.sugar.Web
import com.kaspersky.kaspresso.failure.FailureLoggingParams

class FailedWebAssertionDescriber(
    private val failureLoggingParams: FailureLoggingParams,
    private val assertionCache: AssertionCache
) : FailedAssertionDescriber<Web.WebInteraction<*>> {

    override fun getDescription(interaction: Web.WebInteraction<*>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}