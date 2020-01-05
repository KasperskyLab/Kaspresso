package com.kaspersky.kaspresso.extensions

fun boolWrap(action: () -> Unit): Boolean {
    return try {
        action.invoke()
        true
    } catch (error: Throwable) {
        false
    }
}