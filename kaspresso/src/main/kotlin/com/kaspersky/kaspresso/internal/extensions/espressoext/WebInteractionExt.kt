package com.kaspersky.kaspresso.internal.extensions.espressoext

import android.support.test.espresso.web.sugar.Web
import org.hamcrest.Matcher

/**
 * @return the [Matcher] of [Web.WebInteraction].
 */
internal fun Web.WebInteraction<*>.getMatcher(): Matcher<*> {
    return javaClass
        .getDeclaredField("viewMatcher")
        .apply { isAccessible = true }
        .get(this) as Matcher<*>
}