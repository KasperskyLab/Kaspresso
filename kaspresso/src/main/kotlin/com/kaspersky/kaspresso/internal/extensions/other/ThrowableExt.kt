package com.kaspersky.kaspresso.internal.extensions.other

import com.kaspersky.kaspresso.internal.exceptions.KaspressoError
import com.kaspersky.kaspresso.internal.exceptions.RootViewWithoutFocusWrapperException
import io.reactivex.exceptions.ExtCompositeException

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

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
    return when {
        javaClass.simpleName == "RootViewWithoutFocusException" -> allowed.find {
            it.isAssignableFrom(RootViewWithoutFocusWrapperException::class.java) // RootViewWithoutFocusException class is private, so we cannot access it directly
        } != null

        this is ExtCompositeException -> exceptions.find {
            e: Throwable -> allowed.find { it.isAssignableFrom(e.javaClass) } != null
        } != null

        else -> allowed.find { it.isAssignableFrom(javaClass) } != null
    }
}

/**
 * @return the new [KaspressoError] instance with [failureMessage] and given throwable as a cause if [failureMessage]
 * is not null, or just given throwable otherwise.
 */
internal fun Throwable.withMessage(failureMessage: String?): Throwable =
    failureMessage?.let { KaspressoError(it, this) } ?: this
