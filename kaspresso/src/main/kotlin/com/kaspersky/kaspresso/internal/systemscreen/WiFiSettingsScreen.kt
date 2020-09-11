@file:Suppress("unused")
package com.kaspersky.kaspresso.internal.systemscreen

import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.Switch
import com.kaspersky.components.kautomator.component.switch.UiSwitch
import com.kaspersky.components.kautomator.screen.UiScreen

object WiFiSettingsScreen : UiScreen<WiFiSettingsScreen>() {

    private const val TIMEOUT = 5_000L

    override val packageName: String = "com.android.settings"

    val wifiSwitch: UiSwitch = UiSwitch {
        withClassName(Switch::class.java)
    }

    fun open(context: Context) {
        context.startActivity(
            Intent(Settings.ACTION_WIFI_SETTINGS).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        )
        waitForWindowUpdate(packageName, TIMEOUT)
    }

    fun close(context: Context) {
        pressBack()
        waitForWindowUpdate(context.packageName, TIMEOUT)
    }

    fun enableWifi() {
        wifiSwitch.setChecked(true)
    }

    fun disableWifi() {
        wifiSwitch.setChecked(false)
    }
}
