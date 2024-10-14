package com.kaspersky.kaspresso.params

data class SystemDialogsSafetyParams(
    val shouldIgnoreKeyboard: Boolean,
    val shouldIgnoreCrashes: Boolean,
) {
    companion object {
        fun default() = SystemDialogsSafetyParams(shouldIgnoreKeyboard = false, shouldIgnoreCrashes = false)
    }
}
