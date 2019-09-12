package android.support.test.espresso.assertion

import android.support.test.espresso.ViewAssertion
import org.hamcrest.StringDescription

/**
 * @return a [String] description of [ViewAssertion].
 */
internal fun ViewAssertion?.describe(): String {
    if (this == null) return "null"

    val builder = StringBuilder("Check ")

    when (this) {
        is ViewAssertions.MatchesViewAssertion -> {
            viewMatcher.describeTo(StringDescription(builder))
        }
        else -> {
            builder.append(this::class.java.simpleName)
        }
    }

    return builder.toString()
}