package androidx.test.espresso.assertion

import android.view.View
import androidx.test.espresso.ViewAssertion
import org.hamcrest.Matcher
import org.hamcrest.StringDescription

/**
 * @return a [String] description of [ViewAssertion].
 */
internal fun ViewAssertion?.describe(): String {
    this ?: return "null"

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

internal fun ViewAssertion.getViewMatcher(): Matcher<in View>? {
    if (this is ViewAssertions.MatchesViewAssertion) return viewMatcher
    return null
}