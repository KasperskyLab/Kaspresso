package com.kaspersky.kaspresso.sample_uiautomator_dsl.screen

import com.kaspersky.components.uiautomatordsl.dsl.check.UiCheckBox
import com.kaspersky.components.uiautomatordsl.dsl.edit.UiEditText
import com.kaspersky.components.uiautomatordsl.dsl.screen.UiScreen
import com.kaspersky.components.uiautomatordsl.dsl.text.UiButton

object MainScreen : UiScreen<MainScreen>() {

    private const val MAIN_APP_PACKAGE_ID = "com.kaspersky.kaspresso.sample_uiautomator_dsl"

    val simpleEditText = UiEditText { withId(this@MainScreen.MAIN_APP_PACKAGE_ID, "editText") }
    val simpleButton = UiButton { withId(this@MainScreen.MAIN_APP_PACKAGE_ID, "button") }
    val checkBox = UiCheckBox { withId(this@MainScreen.MAIN_APP_PACKAGE_ID, "checkBox") }
}
