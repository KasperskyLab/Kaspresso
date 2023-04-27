package com.kaspersky.kaspresso.instrumental

import android.app.UiAutomation
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.runner.listener.KaspressoRunNotifier

interface InstrumentalDependencyProvider {

    val isAndroidRuntime: Boolean
    val uiDevice: UiDevice
    val uiAutomation: UiAutomation
    val runNotifier: KaspressoRunNotifier

    @RequiresApi(Build.VERSION_CODES.N)
    fun getUiAutomation(flags: Int): UiAutomation
}
