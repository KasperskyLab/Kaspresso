package com.kaspersky.kaspresso.upgradesample.screen

import com.kaspersky.components.kautomator.screen.UiScreen

@Suppress("UNCHECKED_CAST")
abstract class UpgradeUiScreen<out T : UpgradeUiScreen<T>> : UiScreen<T>() {

    override val packageName: String = "com.kaspersky.kaspressample"
}
