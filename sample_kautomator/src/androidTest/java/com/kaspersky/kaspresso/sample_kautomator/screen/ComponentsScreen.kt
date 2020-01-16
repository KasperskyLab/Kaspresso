package com.kaspersky.kaspresso.sample_kautomator.screen

import com.kaspersky.components.kautomator.dsl.bottomnav.UiBottomNavigationView
import com.kaspersky.components.kautomator.dsl.check.UiCheckBox
import com.kaspersky.components.kautomator.dsl.screen.UiScreen

object ComponentsScreen : UiScreen<ComponentsScreen>() {

    private const val MAIN_APP_PACKAGE_ID = "com.kaspersky.kaspresso.sample_kautomator"

    val bottomNav = UiBottomNavigationView { withId(this@ComponentsScreen.MAIN_APP_PACKAGE_ID, "bottomNav") }
    val checkbox = UiCheckBox { withId(this@ComponentsScreen.MAIN_APP_PACKAGE_ID, "checkBox") }
}