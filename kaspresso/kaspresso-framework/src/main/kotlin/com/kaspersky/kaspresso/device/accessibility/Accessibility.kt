package com.kaspersky.kaspresso.device.accessibility

/**
 * The interface to work with accessibility.
 */
interface Accessibility {

    /**
     * Enables accessibility. Available since api 24.
     *
     * @param packageName a package name of an accessibility service.
     * @param className a class name of an accessibility service.
     */
    fun enable(packageName: String, className: String)

    /**
     * Disables accessibility. Available since api 24.
     */
    fun disable()
}
