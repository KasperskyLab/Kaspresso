package com.kaspersky.kaspresso.extensions.espressoext

import androidx.test.espresso.web.sugar.Web
import org.hamcrest.Matcher

internal fun Web.WebInteraction<*>.getMatcher(): Matcher<*> {
    return javaClass
        .getDeclaredField("viewMatcher")
        .apply { isAccessible = true }
        .get(this) as Matcher<*>
}