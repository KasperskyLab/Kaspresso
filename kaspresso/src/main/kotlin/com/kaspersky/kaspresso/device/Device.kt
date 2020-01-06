package com.kaspersky.kaspresso.device

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.device.accessibility.Accessibility
import com.kaspersky.kaspresso.device.activities.Activities
import com.kaspersky.kaspresso.device.apps.Apps
import com.kaspersky.kaspresso.device.exploit.Exploit
import com.kaspersky.kaspresso.device.files.Files
import com.kaspersky.kaspresso.device.keyboard.Keyboard
import com.kaspersky.kaspresso.device.languages.Language
import com.kaspersky.kaspresso.device.location.Location
import com.kaspersky.kaspresso.device.logcat.Logcat
import com.kaspersky.kaspresso.device.network.Network
import com.kaspersky.kaspresso.device.permissions.HackPermissions
import com.kaspersky.kaspresso.device.permissions.Permissions
import com.kaspersky.kaspresso.device.phone.Phone
import com.kaspersky.kaspresso.device.screenshots.Screenshots

/**
 * The provider of managers for all off-screen work.
 */
data class Device(

    /**
     * Holds the reference to the implementation of [Apps] interface.
     */
    val apps: Apps,

    /**
     * Holds the reference to the implementation of [Activities] interface.
     */
    val activities: Activities,

    /**
     * Holds the reference to the implementation of [Files] interface.
     */
    val files: Files,

    /**
     * Holds the reference to the implementation of [Network] interface.
     */
    val network: Network,

    /**
     * Holds the reference to the implementation of [Phone] interface.
     */
    val phone: Phone,

    /**
     * Holds the reference to the implementation of [Location] interface.
     */
    val location: Location,

    /**
     * Holds the reference to the implementation of [Keyboard] interface.
     */
    val keyboard: Keyboard,

    /**
     * Holds the reference to the implementation of [Screenshots] interface.
     */
    val screenshots: Screenshots,

    /**
     * Holds the reference to the implementation of [Accessibility] interface.
     */
    val accessibility: Accessibility,

    /**
     * Holds the reference to the implementation of [Permissions] interface.
     */
    val permissions: Permissions,

    /**
     * Holds the reference to the implementation of [HackPermissions] interface.
     */
    val hackPermissions: HackPermissions,

    /**
     * Holds the reference to the implementation of [Exploit] interface.
     */
    val exploit: Exploit,

    /**
     * Holds the reference to the implementation of [Language] interface.
     */
    val language: Language,

    /**
     * Holds the reference to the implementation of [Logcat] interface.
     */
    val logcat: Logcat
) {
    /**
     * A not caching property to get [Context].
     */
    val context: Context
        get() = InstrumentationRegistry.getInstrumentation().context

    /**
     * A not caching property to get target [Context].
     */
    val targetContext: Context
        get() = InstrumentationRegistry.getInstrumentation().targetContext

    /**
     * A property to get the instance of [UiDevice].
     */
    val uiDevice: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
}