package com.kaspersky.kaspresso.sample_upgrade_tests.screen

import com.kaspersky.components.kautomator.dsl.screen.UiScreen

@Suppress("UNCHECKED_CAST")
open class UpgradeUiScreen<out T : UpgradeUiScreen<T>> : UiScreen<T>() {

    override val packageName: String = "com.kaspersky.kaspressample"
}
