@file:Suppress("unused")

package com.agoda.kakao.common.matchers

import android.support.design.widget.NavigationView
import android.view.View
import android.support.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description

/**
 * Matches NavigationView with given item id checked
 *
 * @param id menu item id to be checked
 */
class NavigationItemMatcher(private val id: Int) : BoundedMatcher<View, NavigationView>(NavigationView::class.java) {
    override fun describeTo(desc: Description) {
        desc.appendText("Matches view with menu item checked: $id")
    }

    override fun matchesSafely(view: NavigationView)
            = view.menu.getItem(id)?.isChecked ?: false
}