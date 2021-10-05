package com.kaspersky.kaspresso.device.accessibility

import android.annotation.TargetApi
import android.app.UiAutomation
import android.os.Build
import androidx.test.uiautomator.Configurator
import com.kaspersky.kaspresso.instrumental.InstrumentalDepsAssistant

/**
 * The implementation of the [Accessibility] interface.
 */
class AccessibilityImpl(
    private val instrumentalDepsAssistant: InstrumentalDepsAssistant
) : Accessibility {

    /**
     * Enables accessibility. Available since api 24.
     *
     * @param packageName a package name of an accessibility service.
     * @param className a class name of an accessibility service.
     */
    @TargetApi(Build.VERSION_CODES.N)
    override fun enable(packageName: String, className: String) {
        val string = "enabled_accessibility_services"
        val cmd = "settings put secure $string $packageName/$className"

        val flags = UiAutomation.FLAG_DONT_SUPPRESS_ACCESSIBILITY_SERVICES
        Configurator.getInstance().uiAutomationFlags = flags

        instrumentalDepsAssistant
            .getUiAutomation(UiAutomation.FLAG_DONT_SUPPRESS_ACCESSIBILITY_SERVICES)
            .executeShellCommand(cmd)
            .close()
    }

    /**
     * Disables accessibility. Available since api 24.
     */
    @TargetApi(Build.VERSION_CODES.N)
    override fun disable() {
        val string = "enabled_accessibility_services"
        val cmd = "settings put secure $string null"

        instrumentalDepsAssistant
            .getUiAutomation(UiAutomation.FLAG_DONT_SUPPRESS_ACCESSIBILITY_SERVICES)
            .executeShellCommand(cmd)
            .close()
    }
}
