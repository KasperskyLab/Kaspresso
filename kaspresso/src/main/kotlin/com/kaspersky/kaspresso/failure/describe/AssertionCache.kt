package com.kaspersky.kaspresso.failure.describe

import android.view.View
import androidx.test.espresso.ViewAssertion
import org.hamcrest.Matcher

data class AssertionCache(
    internal var cachedViewAssertion: Class<out ViewAssertion>? = null,
    internal var cachedViewMatcher: Matcher<in View>? = null,
    internal var cachedWebMatcher: Matcher<*>? = null
)