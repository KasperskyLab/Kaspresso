package com.kaspersky.uitest_framework.util

import android.content.res.Resources
import android.view.View
import android.widget.TextView
import android.support.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast

fun View?.describe(): String {

    if (this == null) return "null"

    val builder = StringBuilder()

    try {
        builder.append("id=${resources?.getResourceEntryName(id)};")
    } catch (e: Resources.NotFoundException) {
        builder.append("resource id not found;")
    }

    if (!contentDescription.isNullOrBlank()) {
        builder.append("desc=$contentDescription;")
    }

    if (this is TextView) {

        if (!text.isNullOrBlank()) {
            builder.append("text=$text;")
        }

        if (!hint.isNullOrBlank()) {
            builder.append("hint=$hint;")
        }
    }

    return "${this::class.java.simpleName}($builder)"
}

fun View.isDisplayed(): Boolean {
    return isDisplayingAtLeast(VIEW_DISPLAY_THRESHOLD_PERCENT).matches(this)
}

private const val VIEW_DISPLAY_THRESHOLD_PERCENT = 90