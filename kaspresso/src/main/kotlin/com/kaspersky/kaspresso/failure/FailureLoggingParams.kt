package com.kaspersky.kaspresso.failure

import android.view.View
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertionsAndMatcherProvider
import com.kaspersky.kaspresso.obtain_value.actions.ObtainTextViewAction
import com.kaspersky.kaspresso.obtain_value.atom.ObtainValueAtom
import com.kaspersky.kaspresso.obtain_value.actions.ObtainValueViewAction
import com.kaspersky.kaspresso.obtain_value.actions.ObtainVisibilityViewAction
import org.hamcrest.Matcher

data class FailureLoggingParams internal constructor(
    var viewAssertionDescribers: MutableMap<Class<out ViewAssertion>, ViewInteraction.() -> String>,
    var viewMatcherDescribers: MutableMap<Class<out Matcher<in View>>, () -> ObtainValueViewAction>,
    var webMatcherDescribers: MutableMap<Class<out Matcher<*>>, () -> ObtainValueAtom<*>>
) : ViewAssertionsAndMatcherProvider() {

    internal constructor() : this(
        viewAssertionDescribers = hashMapOf(),
        viewMatcherDescribers = hashMapOf(),
        webMatcherDescribers = hashMapOf()
    ) {
        viewMatcherDescribers = hashMapOf(
            WITH_TEXT_MATCHER to { ObtainTextViewAction() },
            WITH_EFFECTIVE_VISIBILITY to { ObtainVisibilityViewAction() }
            // here will be more view matchers
            // users can add their own
        )

        // here will be webMatcherDescribers default initialization

        // here will be viewAssertionDescribers default initialization
//        viewAssertionDescribers = hashMapOf(
//            MATCHES_VIEW_ASSERTION to { ObtainTextViewAction().apply { perform(this) }.value }
//        )
    }
}