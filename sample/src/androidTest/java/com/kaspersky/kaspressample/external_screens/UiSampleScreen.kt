package com.kaspersky.kaspressample.external_screens

import com.kaspersky.components.kautomator.dsl.screen.UiScreen

open class UiSampleScreen<out T : UiSampleScreen<T>> : UiScreen<T>() {

    override val packageName: String = "com.kaspersky.kaspressample"
}