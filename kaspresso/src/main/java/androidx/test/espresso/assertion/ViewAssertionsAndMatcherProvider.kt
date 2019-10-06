package androidx.test.espresso.assertion

import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchersProvider

abstract class ViewAssertionsAndMatcherProvider : ViewMatchersProvider() {

    protected val MATCHES_VIEW_ASSERTION: Class<out ViewAssertion> =
        ViewAssertions.MatchesViewAssertion::class.java

    // Here will be more view assertions
}