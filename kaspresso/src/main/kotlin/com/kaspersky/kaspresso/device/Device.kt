package com.kaspersky.kaspresso.device

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.device.accessibility.Accessibility
import com.kaspersky.kaspresso.device.activities.Activities
import com.kaspersky.kaspresso.device.apps.Apps
import com.kaspersky.kaspresso.device.exploit.Exploit
import com.kaspersky.kaspresso.device.files.Files
import com.kaspersky.kaspresso.device.internet.Internet
import com.kaspersky.kaspresso.device.keyboard.Keyboard
import com.kaspersky.kaspresso.device.location.Location
import com.kaspersky.kaspresso.device.permissions.Permissions
import com.kaspersky.kaspresso.device.phone.Phone
import com.kaspersky.kaspresso.device.screenshots.Screenshots

/**
 * A provider of managers for all off-screen work.
 */
class Device(

    /**
     * Holds an implementation of [Apps] interface. If it was not specified in [Configurator.Builder], the default
     * implementation is used.
     */
    val apps: Apps,

    /**
     * Holds a reference to an implementation of [Activities] interface, held by [Configurator].
     */
    val activities: Activities,

    /**
     * Holds a reference to an implementation of [Files] interface, held by [Configurator].
     */
    val files: Files,

    /**
     * Holds a reference to an implementation of [Internet] interface, held by [Configurator].
     */
    val internet: Internet,

    /**
     * Holds a reference to an implementation of [Phone] interface, held by [Configurator].
     */
    val phone: Phone,

    /**
     * Holds a reference to an implementation of [Location] interface, held by [Configurator].
     */
    val location: Location,

    /**
     * Holds a reference to an implementation of [Keyboard] interface, held by [Configurator].
     */
    val keyboard: Keyboard,

    /**
     * Holds a reference to an implementation of [Screenshots] interface, held by [Configurator].
     */
    val screenshots: Screenshots,

    /**
     * Holds a reference to an implementation of [Accessibility] interface, held by [Configurator].
     */
    val accessibility: Accessibility,

    /**
     * Holds a reference to an implementation of [Permissions] interface, held by [Configurator].
     */
    val permissions: Permissions,

    /**
     * Holds a reference to an implementation of [Exploit] interface, held by [Configurator].
     */
    val exploit: Exploit
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
     * A property to get an instance of [UiDevice].
     */
    val uiDevice: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

}