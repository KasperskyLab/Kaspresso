package com.kaspersky.kaspresso.internal.systemscreen

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.Switch
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.kaspersky.components.kautomator.component.switch.UiSwitch
import com.kaspersky.components.kautomator.screen.UiScreen

object DataUsageSettingsScreen : UiScreen<DataUsageSettingsScreen>() {

    private const val TIMEOUT = 5_000L

    override val packageName: String = "com.android.settings"

    val mobileDataSwitch: UiSwitch = UiSwitch {
        withClassName(Switch::class.java)
    }

    private val uiDevice: UiDevice
        get() = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    fun open(context: Context) {
        context.startActivity(Intent().apply {
            component = ComponentName(packageName,"com.android.settings.Settings\$DataUsageSummaryActivity")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        })
        uiDevice.waitForWindowUpdate(packageName, TIMEOUT)
    }

    fun close(context: Context) {
        uiDevice.pressBack()
        uiDevice.waitForWindowUpdate(context.packageName, TIMEOUT)
    }

    fun enableMobileData() {
        mobileDataSwitch.setChecked(true)
    }

    fun disableMobileData() {
        mobileDataSwitch.setChecked(false)
    }
}