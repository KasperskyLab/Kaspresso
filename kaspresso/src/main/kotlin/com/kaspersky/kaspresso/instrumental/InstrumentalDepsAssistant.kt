package com.kaspersky.kaspresso.instrumental

import android.app.UiAutomation
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.test.uiautomator.UiDevice

interface InstrumentalDepsAssistant {

    val isAndroidRuntime: Boolean
    val uiDevice: UiDevice
    val uiAutomation: UiAutomation

    @RequiresApi(Build.VERSION_CODES.N)
    fun getUiAutomation(flags: Int): UiAutomation
}
