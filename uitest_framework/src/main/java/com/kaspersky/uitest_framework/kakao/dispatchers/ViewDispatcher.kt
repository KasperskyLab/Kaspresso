package com.kaspersky.uitest_framework.kakao.dispatchers

import android.support.test.espresso.*
import android.view.View
import org.hamcrest.Matcher

/**
 * Created by egor.kurnikov on 04.03.2019
 */

open class ViewDispatcher(protected val viewInteraction: ViewInteraction) {

    open fun perform(viewAction: ViewAction): ViewDispatcher {
        viewInteraction.perform(viewAction)
        return this
    }

    open fun check(viewAssertion: ViewAssertion): ViewDispatcher {
        viewInteraction.check(viewAssertion)
        return this
    }

    open fun check(function: (View, NoMatchingViewException) -> Unit): ViewDispatcher {
        viewInteraction.check(function)
        return this
    }

    open fun withFailureHandler(
            function: (error: Throwable, matcher: Matcher<View>) -> Unit
    ): ViewDispatcher {
        viewInteraction.withFailureHandler(function)
        return this
    }

    open fun inRoot(rootMatcher: Matcher<Root>): ViewDispatcher {
        viewInteraction.inRoot(rootMatcher)
        return this
    }
}