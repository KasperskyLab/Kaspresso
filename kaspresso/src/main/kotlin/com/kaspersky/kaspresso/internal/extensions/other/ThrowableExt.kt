package com.kaspersky.kaspresso.internal.extensions.other

import com.kaspersky.kaspresso.internal.exceptions.KaspressoError
import io.reactivex.exceptions.ExtCompositeException

internal inline fun <reified T : Throwable> invokeSafely(
    exceptions: MutableList<T>,
    action: () -> Unit
) {
    try {
        action.invoke()
    } catch (e: Throwable) {
        if (e is T) {
            exceptions.add(e)
        } else {
            throw e
        }
    }
}

internal inline fun <reified ERROR : Throwable, LISTENER> Iterable<LISTENER>.forEachSafely(
    exceptions: MutableList<ERROR>,
    action: (LISTENER) -> Unit
) {
    forEach { invokeSafely(exceptions) { action.invoke(it) } }
}

internal fun <T : Throwable> List<T>.getException(): Throwable? {
    return when (this.size) {
        1 -> this[0]
        in 2..Int.MAX_VALUE -> ExtCompositeException(this)
        else -> null
    }
}

internal fun <T : Throwable> List<T>.throwAll() {
    when (this.size) {
        1 -> throw this[0]
        in 2..Int.MAX_VALUE -> throw ExtCompositeException(this)
    }
}

/**
 * @return true if the given throwable is contained by [allowed] set, false otherwise.
 */
internal fun <T : Throwable> T.isAllowed(allowed: Set<Class<out Throwable>>): Boolean {
    return when (this) {
        is ExtCompositeException -> {
            exceptions.find { e: Throwable ->
                allowed.find { it.isAssignableFrom(e.javaClass) } != null
            } != null
        }
        else -> {
            allowed.find { it.isAssignableFrom(javaClass) } != null
        }
    }
}

/**
 * @return the new [KaspressoError] instance with [failureMessage] and given throwable as a cause if [failureMessage]
 * is not null, or just given throwable otherwise.
 */
internal fun Throwable.withMessage(failureMessage: String?): Throwable =
    failureMessage?.let { KaspressoError(it, this) } ?: this
