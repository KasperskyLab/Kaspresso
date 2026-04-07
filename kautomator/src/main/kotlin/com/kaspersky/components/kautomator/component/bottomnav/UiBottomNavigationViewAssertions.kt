@file:Suppress("unused")
package com.kaspersky.components.kautomator.component.bottomnav

import androidx.test.uiautomator.By
import com.google.common.truth.Truth.assertThat
import com.kaspersky.components.kautomator.component.common.assertions.UiBaseAssertions
import com.kaspersky.components.kautomator.intercept.operation.UiOperationType

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

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
