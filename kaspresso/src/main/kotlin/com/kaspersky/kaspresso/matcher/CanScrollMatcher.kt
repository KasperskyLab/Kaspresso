package com.kaspersky.kaspresso.matcher

import android.view.View
import org.hamcrest.BaseMatcher
import org.hamcrest.Description

/**
 * An implementation of [BaseMatcher] that answers if [View] can be scrolled vertically.
 */
class CanScrollMatcher(
    private val scrollDirection: ScrollDirection
) : BaseMatcher<View>() {

    override fun describeTo(description: Description?) {
        description?.appendText("can scroll at '$scrollDirection' direction")
    }

    override fun matches(item: Any?): Boolean {
        val view: View = item as View

        val direction = when (scrollDirection) {
            ScrollDirection.Up -> 1
            ScrollDirection.Down -> -1
        }

        return view.canScrollVertically(direction)
    }
}