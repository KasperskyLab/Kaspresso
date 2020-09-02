package com.kaspersky.kaspresso.internal.systemscreen

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.widget.Switch
import com.kaspersky.components.kautomator.component.switch.UiSwitch
import com.kaspersky.components.kautomator.screen.UiScreen

object DataUsageSettingsScreen : UiScreen<DataUsageSettingsScreen>() {

    private const val TIMEOUT = 5_000L

    override val packageName: String = "com.android.settings"

    val mobileDataSwitch: UiSwitch = UiSwitch {
        withClassName(Switch::class.java)
    }

    fun open(context: Context) {
        val intent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Intent(Settings.ACTION_DATA_USAGE_SETTINGS).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        } else Intent().apply {
            component = ComponentName(packageName, "com.android.settings.Settings\$DataUsageSummaryActivity")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
        waitForWindowUpdate(packageName, TIMEOUT)
    }

    fun close(context: Context) {
        pressBack()
        waitForWindowUpdate(context.packageName, TIMEOUT)
    }

    fun enableMobileData() {
        mobileDataSwitch.setChecked(true)
    }

    fun disableMobileData() {
        mobileDataSwitch.setChecked(false)
    }
}
