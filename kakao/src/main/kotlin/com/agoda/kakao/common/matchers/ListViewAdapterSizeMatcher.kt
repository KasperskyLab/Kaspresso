@file:Suppress("unused")

package com.agoda.kakao.common.matchers

import android.view.View
import android.widget.ListView
import android.support.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description

/**
 * Matches ListView with count of children
 *
 * @param size of children count in ListView
 */
class ListViewAdapterSizeMatcher(private val size: Int) : BoundedMatcher<View, ListView>(ListView::class.java) {

    private var itemCount: Int = 0

    override fun matchesSafely(view: ListView) = run {
        itemCount = view.adapter?.count ?: 0
        itemCount == size
    }

    override fun describeTo(description: Description) {
        description
                .appendText("ListView with ")
                .appendValue(size)
                .appendText(" item(s), but got with ")
                .appendValue(itemCount)
    }
}