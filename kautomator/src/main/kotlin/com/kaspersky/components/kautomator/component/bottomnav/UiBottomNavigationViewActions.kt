@file:Suppress("unused")
package com.kaspersky.components.kautomator.component.bottomnav

import androidx.test.uiautomator.By
import com.kaspersky.components.kautomator.component.common.actions.UiBaseActions
import com.kaspersky.components.kautomator.intercept.operation.UiOperationType

/**
 * Provides actions for BottomNavigationView
 */
interface UiBottomNavigationViewActions : UiBaseActions {

    /**
     * Selects menu item with given id
     *
     * @param id Menu item id
     */
    fun setSelectedItemWithId(id: String) {
        view.perform(UiBottomNavigationViewActionType.SELECT_WITH_ID) {
            findObject(By.res(applicationPackage, id)).click()
        }
    }

    /**
     * Selects menu item with given index. Note that this method uses view hierarchy which could be changed at any time.
     *
     * @param index Menu item index
     */
    fun setSelectedItemWithIndex(index: Int) {
        view.perform(UiBottomNavigationViewActionType.SELECT_WITH_INDEX) {
            children[0] // ViewGroup with menu items
                .children[index] // Menu item with index
                .click()
        }
    }

    /**
     * Selects menu item with given title. Note that this method uses view hierarchy which could be changed at any time.
     *
     * @param title Menu item title
     */
    fun setSelectedItemWithTitle(title: String) {
        view.perform(UiBottomNavigationViewActionType.SELECT_WITH_TITLE) {
            findObject(By.text(title)).click()
        }
    }

    enum class UiBottomNavigationViewActionType : UiOperationType {
        SELECT_WITH_ID,
        SELECT_WITH_INDEX,
        SELECT_WITH_TITLE
    }
}
