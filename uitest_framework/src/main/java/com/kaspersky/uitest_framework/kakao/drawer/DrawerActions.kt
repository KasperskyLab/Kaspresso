@file:Suppress("unused")

package com.kaspersky.uitest_framework.kakao.drawer

import android.view.Gravity
import android.support.test.espresso.contrib.DrawerActions
import com.kaspersky.uitest_framework.kakao.common.actions.BaseActions

/**
 * Provides actions for navigation drawer
 */
interface DrawerActions : BaseActions {
    /**
     * Opens the navigation drawer
     *
     * @param gravity Gravity to use (Gravity.START by default)
     * @see Gravity.START
     */
    fun open(gravity: Int = Gravity.START) {
        view.perform(DrawerActions.open(gravity))
    }

    /**
     * Closes the navigation drawer
     *
     * @param gravity Gravity to use (Gravity.START by default)
     * @see Gravity.START
     */
    fun close(gravity: Int = Gravity.START) {
        view.perform(DrawerActions.close(gravity))
    }
}