@file:Suppress("unused")

package com.agoda.kakao.common.matchers

import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class SwipeRefreshLayoutMatcher(private val refreshing: Boolean) : TypeSafeMatcher<View>() {
    override fun matchesSafely(item: View?): Boolean {
        return item?.let {
            if (item is SwipeRefreshLayout) {
                item.isRefreshing == refreshing
            } else false
        } ?: false
    }

    override fun describeTo(desc: Description) {
        desc.appendText("with refreshing state: ")
                .appendValue(refreshing)
    }
}
