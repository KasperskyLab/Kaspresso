package com.kaspersky.kaspresso.sample_upgrade_tests.common

import com.kaspersky.components.uiautomatordsl.dsl.screen.UiScreen

@Suppress("UNCHECKED_CAST")
open class UpgradeUiScreen<out T : UpgradeUiScreen<T>> : UiScreen<T>() {

    companion object {
        const val MAIN_APP_PACKAGE_ID = "com.kaspersky.kaspressample"
    }
}
