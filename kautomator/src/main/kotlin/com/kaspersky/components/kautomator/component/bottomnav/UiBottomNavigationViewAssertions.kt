@file:Suppress("unused")
package com.kaspersky.components.kautomator.component.bottomnav

import androidx.test.uiautomator.By
import com.google.common.truth.Truth.assertThat
import com.kaspersky.components.kautomator.component.common.assertions.UiBaseAssertions
import com.kaspersky.components.kautomator.intercept.operation.UiOperationType

/**
 * Provides assertions for BottomNavigationview
 */
interface UiBottomNavigationViewAssertions : UiBaseAssertions {

    /**
     * Checks if the view's selected menu item id matches given one
     *
     * @param id Menu item id
     */
    fun hasSelectedItemWithId(id: String) {
        view.check(UiBottomNavigationViewAssertionType.IS_SELECTED_ITEM_WITH_ID) {
            val item = findObject(By.res(applicationPackage, id))
            assertThat(item.isSelected).isTrue()
        }
    }

    /**
     * Checks if the view's selected menu item id does not match given one.
     *
     * @param id Menu item id
     */
    fun hasNotSelectedItemWithId(id: String) {
        view.check(UiBottomNavigationViewAssertionType.IS_NOT_SELECTED_ITEM_WITH_ID) {
            val item = findObject(By.res(applicationPackage, id))
            assertThat(item.isSelected).isFalse()
        }
    }

    /**
     * Checks if the view's selected menu item index matches given one.
     * Note that this method uses view hierarchy which could be changed at any time.
     *
     * @param index Menu item index
     */
    fun hasSelectedItemWithIndex(index: Int) {
        view.check(UiBottomNavigationViewAssertionType.IS_SELECTED_ITEM_WITH_INDEX) {
            val item = children[0] // ViewGroup with menu items
                .children[index] // Menu item with index
            assertThat(item.isSelected).isTrue()
        }
    }

    /**
     * Checks if the view's selected menu item index does not match given one.
     * Note that this method uses view hierarchy which could be changed at any time.
     *
     * @param index Menu item index
     */
    fun hasNotSelectedItemWithIndex(index: Int) {
        view.check(UiBottomNavigationViewAssertionType.IS_NOT_SELECTED_ITEM_WITH_INDEX) {
            val item = children[0] // ViewGroup with menu items
                .children[index] // Menu item with index
            assertThat(item.isSelected).isFalse()
        }
    }

    /**
     * Checks if the view's selected menu item title matches given one.
     * Note that this method uses view hierarchy which could be changed at any time.
     *
     * @param title Menu item title
     */
    fun hasSelectedItemWithTitle(title: String) {
        view.check(UiBottomNavigationViewAssertionType.IS_SELECTED_ITEM_WITH_TITLE) {
            val item = findObject(By.text(title))
                .parent // BaselineLayout
                .parent // Menu item
            assertThat(item.isSelected).isTrue()
        }
    }

    /**
     * Checks if the view's selected menu item title does not match given one.
     * Note that this method uses view hierarchy which could be changed at any time.
     *
     * @param title Menu item title
     */
    fun hasNotSelectedItemWithTitle(title: String) {
        view.check(UiBottomNavigationViewAssertionType.IS_NOT_SELECTED_ITEM_WITH_TITLE) {
            val item = findObject(By.text(title))
                .parent // BaselineLayout
                .parent // Menu item
            assertThat(item.isSelected).isFalse()
        }
    }

    enum class UiBottomNavigationViewAssertionType : UiOperationType {
        IS_SELECTED_ITEM_WITH_ID,
        IS_NOT_SELECTED_ITEM_WITH_ID,
        IS_SELECTED_ITEM_WITH_INDEX,
        IS_NOT_SELECTED_ITEM_WITH_INDEX,
        IS_SELECTED_ITEM_WITH_TITLE,
        IS_NOT_SELECTED_ITEM_WITH_TITLE
    }
}
