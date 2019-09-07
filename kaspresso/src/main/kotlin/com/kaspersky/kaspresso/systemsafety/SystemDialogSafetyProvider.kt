package com.kaspersky.kaspresso.systemsafety

interface SystemDialogSafetyProvider {

    fun <R> passSystemDialogs(action: () -> R): R
}