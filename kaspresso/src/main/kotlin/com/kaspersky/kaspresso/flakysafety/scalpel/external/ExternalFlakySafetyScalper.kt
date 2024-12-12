package com.kaspersky.kaspresso.flakysafety.scalpel.external

interface ExternalFlakySafetyScalper {
    fun isFlakySafetyInterceptorPresent(): Boolean
    fun scalpFlakySafety()
    fun restoreFlakySafety()
}
