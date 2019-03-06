@file:Suppress("unused")

package com.kaspersky.uitest_framework.kakao.common.matchers

import android.view.View
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.v7.widget.RecyclerView
import org.hamcrest.Description

/**
 * Matches RecyclerView with count of children
 *
 * @param size of children count in RecyclerView
 */
class RecyclerViewAdapterSizeMatcher(private val size: Int) : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {

    private var itemCount: Int = 0

    override fun matchesSafely(view: RecyclerView) = run {
        itemCount = view.adapter?.itemCount ?: 0
        itemCount == size
    }

    override fun describeTo(description: Description) {
        description
                .appendText("RecyclerView with ")
                .appendValue(size)
                .appendText(" item(s), but got with ")
                .appendValue(itemCount)
    }
}