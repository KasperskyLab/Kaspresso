package com.kaspersky.kaspresso.params

import java.util.concurrent.TimeUnit

/**
 * @param shouldIgnoreKeyboard by default kaspresso treats the keyboard as a system dialog. Enabling this flag prevents a keyboard closing
 * @param shouldIgnoreCrashes whether kaspresso should check if there's a system crash dialog. Be aware that enabling it increases a time spent
 * on a dialog detection about `waitTimeout` * 2
 * @param shouldIgnorePermissionDialogs whether kaspresso should try to automagically close the permission dialogs
 * @param waitTimeout the time spent on a try to detect each of a single type of a dialog
 *
 * @see com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProviderImpl.isAndroidSystemDetected
 * @see com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyPattern
 */
data class SystemDialogsSafetyParams(
    val shouldIgnoreKeyboard: Boolean,
    val shouldIgnoreCrashes: Boolean,
    val waitTimeout: Long = TimeUnit.SECONDS.toMillis(1),
    val shouldIgnorePermissionDialogs: Boolean,
) {
    companion object {
        fun default() = SystemDialogsSafetyParams(
            shouldIgnoreKeyboard = false,
            shouldIgnoreCrashes = true,
            shouldIgnorePermissionDialogs = false
        )
    }
}
