@file:Suppress("unused")

package com.kaspersky.uitest_framework.kakao.navigation

import android.support.test.espresso.assertion.ViewAssertions
import com.kaspersky.uitest_framework.kakao.common.assertions.BaseAssertions
import com.kaspersky.uitest_framework.kakao.common.matchers.NavigationItemMatcher

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