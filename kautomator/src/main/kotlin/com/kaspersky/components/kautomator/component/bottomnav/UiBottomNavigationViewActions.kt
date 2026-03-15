@file:Suppress("unused")
package com.kaspersky.components.kautomator.component.bottomnav

import androidx.test.uiautomator.By
import com.kaspersky.components.kautomator.component.common.actions.UiBaseActions
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
