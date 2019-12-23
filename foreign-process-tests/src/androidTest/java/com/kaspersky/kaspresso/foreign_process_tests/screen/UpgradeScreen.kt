package com.kaspersky.kaspresso.foreign_process_tests.screen

import com.kaspersky.components.uiautomator_dsl.dsl.edit.UiEditText
import com.kaspersky.components.uiautomator_dsl.dsl.text.UiButton
import com.kaspersky.components.uiautomator_dsl.dsl.text.UiTextView
import com.kaspersky.kaspresso.foreign_process_tests.common.UpgradeUiScreen

object UpgradeScreen : UpgradeUiScreen<UpgradeScreen>() {

    val version = UiTextView { withId(MAIN_APP_PACKAGE_ID, "upgrade_version") }
    val input = UiEditText { withId(MAIN_APP_PACKAGE_ID, "upgrade_value_input") }
    val applyBtn = UiButton { withId(MAIN_APP_PACKAGE_ID, "upgrade_apply_btn") }
    val value = UiTextView { withId(MAIN_APP_PACKAGE_ID, "upgrade_value_current") }
}
