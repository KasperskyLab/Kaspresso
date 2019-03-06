@file:Suppress("unused")

package com.kaspersky.uitest_framework.kakao.bottomnav

import android.support.design.widget.BottomNavigationView
import com.kaspersky.uitest_framework.kakao.common.assertions.BaseAssertions

/**
 * Provides assertion for BottomNavigationview
 */
interface BottomNavigationViewAssertions : BaseAssertions {
    /**
     * Checks if the view's selected item id matches given one
     *
     * @param id Menu item id to be checked
     */
    fun hasSelectedItem(id: Int) {
        view.check { view, notFoundException ->
            if (view is BottomNavigationView) {
                if (view.selectedItemId != id) {
                    throw AssertionError("Expected selected item id is $id," +
                            " but actual is ${view.selectedItemId}")
                }
            } else {
                notFoundException?.let {
                    throw AssertionError(it)
                }
            }
        }
    }
}
