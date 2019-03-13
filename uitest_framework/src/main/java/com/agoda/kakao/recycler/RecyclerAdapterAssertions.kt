@file:Suppress("unused")

package com.agoda.kakao.recycler

import android.support.test.espresso.assertion.ViewAssertions
import com.agoda.kakao.common.assertions.AdapterAssertions
import com.agoda.kakao.common.matchers.RecyclerViewAdapterSizeMatcher

/**
 * Provides assertions for recyclerView adapter
 */
interface RecyclerAdapterAssertions : AdapterAssertions {
    /**
     * Check size of recycler view
     *
     * @param size expected child count size in recycler view
     */
    fun hasSize(size: Int) {
        view.check(ViewAssertions.matches(RecyclerViewAdapterSizeMatcher(size)))
    }
}