package androidx.test.espresso.matcher

import android.view.View
import org.hamcrest.Matcher

abstract class ViewMatchersProvider {

    protected val WITH_TEXT_MATCHER: Class<out Matcher<in View>> =
        ViewMatchers.WithTextMatcher::class.java

    protected val WITH_EFFECTIVE_VISIBILITY: Class<out Matcher<in View>> =
        ViewMatchers.WithEffectiveVisibilityMatcher::class.java
}