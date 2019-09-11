package com.kaspersky.kaspresso.internal.exceptions

/**
 * Exception with no stacktrace to hold an assertion message and the initial cause of the exception.
 * Stacktrace is obsolete because the [cause] contains useful information which guides to the user's test.
 * It is derived from [java.lang.Error] instead of [java.lang.AssertionError] since
 * the desired constructor with a [message] and [cause] is not supported in Java 6.
 */
internal class KaspressoError(message: String, cause: Throwable) : java.lang.Error(message, cause) {

    /**
     *  Do not fill stacktrace
     */
    override fun fillInStackTrace(): Throwable = this
}
