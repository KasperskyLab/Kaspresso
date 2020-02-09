package com.kaspersky.kaspressample.external_screens

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.kaspersky.components.kautomator.dsl.screen.UiScreen

open class UiSampleScreen<out T : UiSampleScreen<T>> : UiScreen<T>() {

    override val packageName: String = "com.kaspersky.kaspressample"

    protected val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
}