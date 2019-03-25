package com.kaspersky.kaspresso.device.accessibility

import android.annotation.TargetApi
import android.app.UiAutomation
import android.os.Build
import android.support.test.InstrumentationRegistry

/**
 * An implementation of Accessibility interface.
 */
class AccessibilityImpl : Accessibility {

    /**
     * Enables accessibility. Available since api 24.
     *
     * @param packageName a package name of an accessibility service
     * @param className a class name of an accessibility service
     */
    @TargetApi(Build.VERSION_CODES.N)
    override fun enable(
        packageName: String,
        className: String
    ) {
        val string = "enabled_accessibility_services"
        val cmd = "settings put secure $string $packageName/$className"

        InstrumentationRegistry.getInstrumentation()
            .getUiAutomation(UiAutomation.FLAG_DONT_SUPPRESS_ACCESSIBILITY_SERVICES)
            .executeShellCommand(cmd)
            .close()
    }

    /**
     * Enables accessibility. Available since api 24.
     */
    @TargetApi(Build.VERSION_CODES.N)
    override fun disable() {
        val string = "enabled_accessibility_services"
        val cmd = "settings put secure $string ' '"

        InstrumentationRegistry.getInstrumentation()
            .getUiAutomation(UiAutomation.FLAG_DONT_SUPPRESS_ACCESSIBILITY_SERVICES)
            .executeShellCommand(cmd)
            .close()
    }
}