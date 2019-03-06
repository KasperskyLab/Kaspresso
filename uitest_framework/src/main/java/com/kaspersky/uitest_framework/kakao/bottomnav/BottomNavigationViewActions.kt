@file:Suppress("unused")

package com.kaspersky.uitest_framework.kakao.bottomnav

import android.support.design.widget.BottomNavigationView
import android.view.View
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.ViewMatchers
import com.kaspersky.uitest_framework.kakao.common.actions.BaseActions

/**
 * Provides actions for BottomNavigationView
 */
interface BottomNavigationViewActions : BaseActions {
    /**
     * Sets the given item id as selected
     *
     * @param id menu item id to be set
     */
    fun setSelectedItem(id: Int) {
        view.perform(object : ViewAction {
            override fun getDescription() = "Sets given item id as selected: $id"

            override fun getConstraints() = ViewMatchers.isAssignableFrom(BottomNavigationView::class.java)

            override fun perform(uiController: UiController, view: View) {
                if (view is BottomNavigationView) {
                    view.selectedItemId = id
                }
            }
        })
    }
}
