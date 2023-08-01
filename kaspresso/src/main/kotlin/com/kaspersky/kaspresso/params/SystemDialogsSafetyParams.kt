package com.kaspersky.kaspresso.params

data class SystemDialogsSafetyParams(
    val shouldIgnoreKeyboard: Boolean
) {
    companion object {
        fun default() = SystemDialogsSafetyParams(shouldIgnoreKeyboard = false)
    }
}
