package com.kaspersky.uitest_framework.espressoext.matchers

import android.view.View
import com.kaspersky.uitest_framework.util.AppendableDescription
import org.hamcrest.Matcher

fun Matcher<View>?.describe(): String {

    if (this == null) return "null"

    val builder = StringBuilder()

    this.describeTo(AppendableDescription(builder))

    return builder.toString()
}