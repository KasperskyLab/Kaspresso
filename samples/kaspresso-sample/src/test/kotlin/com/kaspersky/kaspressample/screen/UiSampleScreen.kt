package com.kaspersky.kaspressample.screen

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.kaspersky.components.kautomator.screen.UiScreen

abstract class UiSampleScreen<out T : UiSampleScreen<T>> : UiScreen<T>() {

    override val packageName: String = "com.kaspersky.kaspressample"
    abstract val layoutId: Int?
    abstract val viewClass: Class<*>?

    protected val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
}
