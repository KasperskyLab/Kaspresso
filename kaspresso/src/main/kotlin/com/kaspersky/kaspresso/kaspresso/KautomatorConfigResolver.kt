package com.kaspersky.kaspresso.kaspresso

object KautomatorConfigResolver {

    fun build(sharedTest: Boolean): KautomatorConfig =
        when (sharedTest) {
            true -> KautomatorJvmConfig()
            false -> KautomatorInstrumentationConfig()
        }
}
