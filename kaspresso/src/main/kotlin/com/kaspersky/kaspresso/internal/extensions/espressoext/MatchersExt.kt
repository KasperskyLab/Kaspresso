package com.kaspersky.kaspresso.internal.extensions.espressoext

import android.view.View
import org.hamcrest.Matcher
import org.hamcrest.StringDescription

/**
 * @return a [String] description of [Matcher].
 */
internal fun Matcher<View>?.describe(): String {
    if (this == null) return "null"

    val builder = StringBuilder()
    this.describeTo(StringDescription(builder))

    return builder.toString()
}
