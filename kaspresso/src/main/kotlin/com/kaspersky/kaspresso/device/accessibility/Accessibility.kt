package com.kaspersky.kaspresso.device.accessibility

/**
 * An interface to work with accessibility.
 */
interface Accessibility {

    companion object {
        private const val KASPERSKY_ACCESSIBILITY_SERVICE_CLASS_NAME =
            "com.kaspersky.components.accessibility.KasperskyAccessibility"
    }

    /**
     * Enables accessibility. Available since api 24.
     *
     * @param packageName a package name of an accessibility service
     * @param className a class name of an accessibility service
     */
    fun enable(
        packageName: String,
        className: String = KASPERSKY_ACCESSIBILITY_SERVICE_CLASS_NAME
    )

    /**
     * Enables accessibility. Available since api 24.
     */
    fun disable()
}