@file:Suppress("unused")
package com.kaspersky.components.kautomator.component.chip

import androidx.test.uiautomator.By
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiObject2
import com.google.common.truth.Truth.assertThat
import com.kaspersky.components.kautomator.component.common.assertions.UiBaseAssertions
import com.kaspersky.components.kautomator.intercept.operation.UiOperationType
import java.util.regex.Pattern

/**
 * Provides assertions for a ChipGroup
 */
interface UiChipGroupAssertions : UiBaseAssertions {

    /**
     * Checks if chip with given text exists
     *
     * @param text Chip text
     */
    fun hasChipWithText(text: String) {
        view.check(UiChipGroupAssertionType.HAS_CHIP) {
            findChipAndCheckNotNull(this, By.text(text))
        }
    }

    /**
     * Checks if chip with given text exists
     *
     * @param pattern Chip text pattern
     */
    fun hasChipWithText(pattern: Pattern) {
        view.check(UiChipGroupAssertionType.HAS_CHIP) {
            findChipAndCheckNotNull(this, By.text(pattern))
        }
    }

    /**
     * Checks if chip with given id is selected
     *
     * @param id Chip id
     */
    fun isChipWithIdSelected(id: String) {
        view.check(UiChipGroupAssertionType.IS_CHIP_WITH_ID_SELECTED) {
            val chip = findChipAndCheckNotNull(this, By.res(applicationPackage, id))
            assertThat(chip.isChecked).isTrue()
        }
    }

    /**
     * Checks if chip with given id is not selected
     *
     * @param id Chip id for check
     */
    fun isNotChipWithIdSelected(id: String) {
        view.check(UiChipGroupAssertionType.IS_NOT_CHIP_WITH_ID_SELECTED) {
            val chip = findChipAndCheckNotNull(this, By.res(applicationPackage, id))
            assertThat(chip.isChecked).isFalse()
        }
    }

    /**
     * Checks if chip with given text is selected
     *
     * @param text Chip text
     */
    fun isChipWithTextSelected(text: String) {
        view.check(UiChipGroupAssertionType.IS_CHIP_WITH_TEXT_SELECTED) {
            val chip = findChipAndCheckNotNull(this, By.text(text))
            assertThat(chip.isChecked).isTrue()
        }
    }

    /**
     * Checks if chip with given text is not selected
     *
     * @param text Chip text
     */
    fun isNotChipWithTextSelected(text: String) {
        view.check(UiChipGroupAssertionType.IS_NOT_CHIP_WITH_TEXT_SELECTED) {
            val chip = findChipAndCheckNotNull(this, By.text(text))
            assertThat(chip.isChecked).isFalse()
        }
    }

    /**
     * Checks if chip with given text pattern is selected
     *
     * @param pattern Chip text pattern
     */
    fun isChipWithTextSelected(pattern: Pattern) {
        view.check(UiChipGroupAssertionType.IS_CHIP_WITH_TEXT_SELECTED) {
            val chip = findChipAndCheckNotNull(this, By.text(text))
            assertThat(chip.isChecked).isTrue()
        }
    }

    /**
     * Checks if chip with given text pattern is not selected
     *
     * @param pattern Chip text pattern
     */
    fun isNotChipWithTextSelected(pattern: Pattern) {
        view.check(UiChipGroupAssertionType.IS_NOT_CHIP_WITH_TEXT_SELECTED) {
            val chip = findChipAndCheckNotNull(this, By.text(text))
            assertThat(chip.isChecked).isFalse()
        }
    }

    /**
     * Checks if chip with given index is selected
     *
     * @param index Chip index in ChipGroup
     */
    fun isChipWithIndexSelected(index: Int) {
        view.check(UiChipGroupAssertionType.IS_CHIP_WITH_INDEX_SELECTED) {
            val chip = children[index]
            assertThat(chip).isNotNull()
            assertThat(chip.isChecked).isTrue()
        }
    }

    /**
     * Checks if chip with given index is not selected
     *
     * @param index Chip index in ChipGroup]
     */
    fun isNotChipWithIndexSelected(index: Int) {
        view.check(UiChipGroupAssertionType.IS_NOT_CHIP_WITH_INDEX_SELECTED) {
            val chip = children[index]
            assertThat(chip).isNotNull()
            assertThat(chip.isChecked).isFalse()
        }
    }

    private fun findChipAndCheckNotNull(obj: UiObject2, selector: BySelector): UiObject2 {
        val chip = obj.findObject(selector)
        assertThat(chip).isNotNull()
        return chip
    }

    enum class UiChipGroupAssertionType : UiOperationType {
        HAS_CHIP,
        IS_CHIP_WITH_ID_SELECTED,
        IS_NOT_CHIP_WITH_ID_SELECTED,
        IS_CHIP_WITH_TEXT_SELECTED,
        IS_NOT_CHIP_WITH_TEXT_SELECTED,
        IS_CHIP_WITH_INDEX_SELECTED,
        IS_NOT_CHIP_WITH_INDEX_SELECTED
    }
}