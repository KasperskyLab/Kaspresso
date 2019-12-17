package com.kaspersky.kaspresso.foreign_process_tests.screen

import com.kaspersky.components.uiautomator_dsl.edit.UiEditText
import com.kaspersky.components.uiautomator_dsl.text.UiButton
import com.kaspersky.components.uiautomator_dsl.text.UiTextView
import com.kaspersky.kaspresso.foreign_process_tests.common.UpgradeUiScreen

object UpgradeScreen : UpgradeUiScreen<UpgradeScreen>() {

    private const val VERSION_ID = "upgrade_version"
    private const val INPUT_ID = "upgrade_value_input"
    private const val APPLY_BTN_ID = "upgrade_apply_btn"
    private const val VALUE_TEXT = "upgrade_value_current"

    val version = UiTextView { withId(MAIN_APP_PACKAGE_ID, VERSION_ID) }
    val input = UiEditText { withId(MAIN_APP_PACKAGE_ID, INPUT_ID) }
    val applyBtn = UiButton { withId(MAIN_APP_PACKAGE_ID, APPLY_BTN_ID) }
    val value = UiTextView { withId(MAIN_APP_PACKAGE_ID, VALUE_TEXT) }
}
