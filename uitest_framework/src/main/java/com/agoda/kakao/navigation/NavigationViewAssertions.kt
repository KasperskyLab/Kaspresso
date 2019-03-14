@file:Suppress("unused")

package com.agoda.kakao.navigation

import android.support.test.espresso.assertion.ViewAssertions
import com.agoda.kakao.common.assertions.BaseAssertions
import com.agoda.kakao.common.matchers.NavigationItemMatcher

/**
 * Provides assertions for NavigationView
 */
interface NavigationViewAssertions : BaseAssertions {
    /**
     * Checks if NavigationView has checked given item id
     *
     * @param id menu item expected to be checked
     */
    fun isItemChecked(id: Int) {
        view.check(ViewAssertions.matches(NavigationItemMatcher(id)))
    }
}