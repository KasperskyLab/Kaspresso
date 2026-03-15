@file:Suppress("unused")
package com.kaspersky.components.kautomator.component.chip

import androidx.test.uiautomator.By
import com.kaspersky.components.kautomator.component.common.actions.UiBaseActions
import com.kaspersky.components.kautomator.intercept.operation.UiOperationType
import java.util.regex.Pattern

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
 * Provides actions for a ChipGroup
 */
interface UiChipGroupActions : UiBaseActions {

    /**
     * Selects a chip in ChipGroup with given id
     *
     * @param id Chip id to match
     */
    fun selectChipWithId(id: String) {
        view.perform(UiChipGroupActionType.SELECT_WITH_ID) {
            findObject(By.res(applicationPackage, id)).click()
        }
    }

    /**
     * Selects a chip with given index
     *
     * @param index ChipGroup Chip index
     */
    fun selectChipWithIndex(index: Int) {
        view.perform(UiChipGroupActionType.SELECT_WITH_INDEX) {
            children[index].click()
        }
    }

    /**
     * Selects a chip with given text
     *
     * @param text Chip text to match
     */
    fun selectChipWithText(text: String) {
        view.perform(UiChipGroupActionType.SELECT_WITH_TEXT) {
            findObject(By.text(text)).click()
        }
    }

    /**
     * Selects a chip with give text pattern
     *
     * @param pattern Chip text pattern to match
     */
    fun selectChipWithText(pattern: Pattern) {
        view.perform(UiChipGroupActionType.SELECT_WITH_TEXT) {
            findObject(By.text(pattern)).click()
        }
    }

    enum class UiChipGroupActionType : UiOperationType {
        SELECT_WITH_ID,
        SELECT_WITH_INDEX,
        SELECT_WITH_TEXT
    }
}
