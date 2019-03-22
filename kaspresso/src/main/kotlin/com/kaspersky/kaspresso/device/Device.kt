package com.kaspersky.kaspresso.device

import android.content.Context
import android.os.Build
import android.support.test.InstrumentationRegistry
import android.support.test.uiautomator.*
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.device.accessibility.Accessibility
import com.kaspersky.kaspresso.device.activities.Activities
import com.kaspersky.kaspresso.device.files.Files
import com.kaspersky.kaspresso.device.internet.Internet
import com.kaspersky.kaspresso.device.apps.Apps
import com.kaspersky.kaspresso.device.exploit.Exploit
import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.device.permissions.Permissions

/**
 * A provider of managers for all off-screen work.
 */
object Device {

    val context: Context
        get() = InstrumentationRegistry.getInstrumentation().context

    val uiDevice: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    val apps: Apps = Configurator.apps
    val activities: Activities = Configurator.activities
    var files: Files = Configurator.files
    var internet: Internet = Configurator.internet
    var screenshots: Screenshots = Configurator.screenshots
    var accessibility: Accessibility = Configurator.accessibility
    var permissions: Permissions = Configurator.permissions
    var exploit: Exploit = Configurator.exploit
}