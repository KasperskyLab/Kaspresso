package androidx.test.espresso.assertion

import androidx.test.espresso.ViewAssertion
import org.hamcrest.StringDescription

/**
 * @return a [String] description of [ViewAssertion].
 */
fun ViewAssertion?.describe(): String {
    if (this == null) return "null"

    val builder = StringBuilder("Check ")

    if (this is ViewAssertions.MatchesViewAssertion) {
        this.viewMatcher.describeTo(StringDescription(builder))
    } else {
        builder.append(this::class.java.simpleName)
    }

    return builder.toString()
}