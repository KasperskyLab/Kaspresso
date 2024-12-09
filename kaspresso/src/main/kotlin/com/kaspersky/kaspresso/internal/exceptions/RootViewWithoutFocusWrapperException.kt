package com.kaspersky.kaspresso.internal.exceptions

import java.lang.RuntimeException

/**
 * A wrapper for a RootViewWithoutFocusException. It's needed because RootViewWithoutFocusException is a private class,
 * so we have to use reflection to catch it and provide a user a sane way to control it.
 *
 * @see com.kaspersky.kaspresso.params.FlakySafetyParams.Companion.getDefaultAllowedExceptions
 * @see com.kaspersky.kaspresso.internal.extensions.other.ThrowableExtKt.isAllowed
 */
class RootViewWithoutFocusWrapperException : RuntimeException()
