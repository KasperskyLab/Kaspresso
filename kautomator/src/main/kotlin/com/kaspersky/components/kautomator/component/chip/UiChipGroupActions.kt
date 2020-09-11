@file:Suppress("unused")
package com.kaspersky.components.kautomator.component.chip

import androidx.test.uiautomator.By
import com.kaspersky.components.kautomator.component.common.actions.UiBaseActions
import com.kaspersky.components.kautomator.intercept.operation.UiOperationType
import java.util.regex.Pattern

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
