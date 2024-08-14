package com.kaspersky.kaspresso.params

data class SystemDialogsSafetyParams(
    val shouldIgnoreKeyboard: Boolean,
    val shouldIgnorePermissionController: Boolean
) {
    companion object {
        fun default() = SystemDialogsSafetyParams(
            shouldIgnoreKeyboard = false,
            shouldIgnorePermissionController = false
        )
    }
}
