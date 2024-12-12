package com.kaspersky.kaspresso.flakysafety.scalpel.external

interface ExternalFlakySafetyScalperNotifier {
    fun addScalper(scalper: ExternalFlakySafetyScalper)

    fun isAnyExternalFlakySafetyInterceptorPresent(): Boolean
    fun scalpFlakySafety()
    fun restoreFlakySafety()
}

internal class ExternalFlakySafetyScalperNotifierImpl : ExternalFlakySafetyScalperNotifier {
    private val scalpers = mutableListOf<ExternalFlakySafetyScalper>()

    override fun addScalper(scalper: ExternalFlakySafetyScalper) {
        synchronized(this) {
            scalpers.add(scalper)
        }
    }

    override fun isAnyExternalFlakySafetyInterceptorPresent(): Boolean {
        synchronized(this) {
            return scalpers.any { it.isFlakySafetyInterceptorPresent() }
        }
    }

    override fun scalpFlakySafety() = scalpers.forEach {
        it.scalpFlakySafety()
    }

    override fun restoreFlakySafety() = scalpers.forEach {
        it.restoreFlakySafety()
    }
}
