package com.kaspersky.kaspresso.idlewaiting

class KautomatorWaitForIdleSettings constructor(
    val waitForSelectorTimeout: Long,
    val waitForIdleTimeout: Long
) {

    companion object {
        private const val DEFAULT_UIAUTOMATOR_VALUE: Long = 10_000
        private const val BOOST_UIAUTOMATOR_VALUE: Long = 0

        fun default() = KautomatorWaitForIdleSettings(
            waitForSelectorTimeout = DEFAULT_UIAUTOMATOR_VALUE,
            waitForIdleTimeout = DEFAULT_UIAUTOMATOR_VALUE
        )
        fun boost() = KautomatorWaitForIdleSettings(
            waitForSelectorTimeout = BOOST_UIAUTOMATOR_VALUE,
            waitForIdleTimeout = BOOST_UIAUTOMATOR_VALUE
        )
    }
}
