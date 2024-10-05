package com.kaspersky.kaspresso.params

data class SystemDialogsSafetyParams(
    val shouldIgnoreKeyboard: Boolean,
    val shouldIgnorePermissionDialogs: Boolean
) {
    companion object {
        fun default() = SystemDialogsSafetyParams(
            shouldIgnoreKeyboard = false,
            shouldIgnorePermissionDialogs = false
        )
    }
}
