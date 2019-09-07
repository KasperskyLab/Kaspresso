package com.kaspersky.kaspresso.systemsafety

interface SystemDialogSafetyProvider {

    fun <T> passSystemDialogs(action: () -> T): T
}