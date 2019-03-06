@file:Suppress("unused")

package com.kaspersky.uitest_framework.kakao.navigation

import android.support.test.espresso.contrib.NavigationViewActions
import com.kaspersky.uitest_framework.kakao.common.actions.BaseActions

/**
 * Provides actions for navigation view
 */
interface NavigationViewActions : BaseActions {
    /**
     * Clicks on the navigation view menu item with given id
     *
     * @param id Menu id to be navigated to
     */
    fun navigateTo(id: Int) {
        view.perform(NavigationViewActions.navigateTo(id))
    }
}