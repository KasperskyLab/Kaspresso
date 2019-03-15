@file:Suppress("unused")

package com.agoda.kakao.tabs

import android.support.design.widget.BottomNavigationView
import android.support.design.widget.TabLayout
import android.view.View
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.ViewMatchers
import com.agoda.kakao.common.actions.BaseActions

/**
 * Provides action for TabLayout
 */
interface TabLayoutActions : BaseActions {
    /**
     * Selects tab at given index
     *
     * @param index tab index to be selected
     */
    fun selectTab(index: Int) {
        view.perform(object : ViewAction {
            override fun getDescription() = "Selects the tab at index: $index"

            override fun getConstraints() = ViewMatchers.isAssignableFrom(TabLayout::class.java)

            override fun perform(uiController: UiController, view: View) {
                if (view is TabLayout) {
                    view.getTabAt(index)!!.select()
                }
            }
        })
    }

    /**
     * Returns the currently selected item id
     *
     * @return selected menu item id
     */
    fun getSelectedItem(): Int {
        var id = 0

        view.perform(object : ViewAction {
            override fun getDescription() = "Gets selected item id"

            override fun getConstraints() = ViewMatchers.isAssignableFrom(BottomNavigationView::class.java)

            override fun perform(uiController: UiController, view: View) {
                if (view is BottomNavigationView) {
                    id = view.selectedItemId
                }
            }
        })

        return id
    }
}