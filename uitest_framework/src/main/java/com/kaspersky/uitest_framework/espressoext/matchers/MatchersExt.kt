package com.kaspersky.uitest_framework.espressoext.matchers

import android.view.View
import org.hamcrest.Matcher
import org.hamcrest.StringDescription

/**
 * @return a string description of [Matcher]
 */
fun Matcher<View>?.describe(): String {

    if (this == null) return "null"

    val builder = StringBuilder()

    this.describeTo(StringDescription(builder))

    return builder.toString()
}