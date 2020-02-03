package com.kaspersky.kaspressample.external_screens

import com.kaspersky.components.kautomator.dsl.edit.UiEditText

object UiWaitForIdleScreen : UiSampleScreen<UiWaitForIdleScreen>() {

    val edit = UiEditText { withId(this@UiWaitForIdleScreen.packageName, "edit") }
}