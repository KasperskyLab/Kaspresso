package com.kaspersky.kaspressample.external_screens

import com.kaspersky.components.uiautomatordsl.dsl.text.UiButton

object UiMainScreen : UiSampleScreen<UiMainScreen>() {

    val scrollViewStubButton = UiButton { withId(PACKAGE, "activity_main_button_scroll_view_sample") }
}