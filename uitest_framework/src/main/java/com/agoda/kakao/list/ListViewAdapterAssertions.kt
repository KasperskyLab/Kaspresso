@file:Suppress("unused")

package com.agoda.kakao.list

import android.support.test.espresso.assertion.ViewAssertions
import com.agoda.kakao.common.assertions.AdapterAssertions
import com.agoda.kakao.common.matchers.ListViewAdapterSizeMatcher

/**
 * Provides assertions for listView adapter
 */
interface ListViewAdapterAssertions : AdapterAssertions {
    /**
     * Check size of recycler view
     *
     * @param size expected child count size in recycler view
     */
    fun hasSize(size: Int) {
        view.check(ViewAssertions.matches(ListViewAdapterSizeMatcher(size)))
    }
}