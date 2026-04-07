package com.kaspersky.kaspresso.runner.listener

import android.app.Instrumentation
import com.kaspersky.kaspresso.runner.KaspressoRunner
import org.junit.runner.Description
import org.junit.runner.Result
import org.junit.runner.notification.Failure

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

interface KaspressoRunNotifier : KaspressoRunListener {
    val listeners: List<KaspressoRunListener>
    fun addListener(listener: KaspressoRunListener)
}

inline fun <reified L : KaspressoRunListener> KaspressoRunNotifier.addUniqueListener(createListener: () -> L) {
    if (listeners.filterIsInstance<L>().isEmpty()) {
        addListener(createListener())
    }
}

inline fun <reified L : KaspressoRunListener> KaspressoRunNotifier.getUniqueListener(): L {
    return listeners.filterIsInstance<L>().takeIf { it.size == 1 }?.first()
        ?: throw IllegalStateException("Unique listener is not single")
}

fun Instrumentation.getKaspressoRunNotifier(): KaspressoRunNotifier =
    requireNotNull((this as? KaspressoRunner)?.runNotifier) {
        "com.kaspersky.kaspresso.runner.KaspressoRunner is not set"
    }

internal class KaspressoRunNotifierImpl : KaspressoRunNotifier {
    private val cache = KaspressoLateRunListener.Cache()
    override val listeners: MutableList<KaspressoRunListener> = arrayListOf()

    override fun addListener(listener: KaspressoRunListener) {
        if (listener is KaspressoLateRunListener) {
            /*
            Currently, "late" listeners are used for Allure reports hack. They are added in runtime
            after an actual test was started so they won't receive the first "test start" callbacks,
            thus we have to call them manually on our side.
             */
            listener.lateInit(cache)
        }
        listeners.add(listener)
    }

    override fun testRunStarted(description: Description) {
        cache.testRunStarted(description)
        listeners.forEach { it.testRunStarted(description) }
    }

    override fun testStarted(description: Description) {
        cache.testStarted(description)
        listeners.forEach { it.testStarted(description) }
    }

    override fun testFinished(description: Description) {
        listeners.forEach { it.testFinished(description) }
    }

    override fun testFailure(failure: Failure) {
        listeners.forEach { it.testFailure(failure) }
    }

    override fun testAssumptionFailure(failure: Failure) {
        listeners.forEach { it.testAssumptionFailure(failure) }
    }

    override fun testIgnored(description: Description) {
        listeners.forEach { it.testIgnored(description) }
    }

    override fun testRunFinished(result: Result) {
        listeners.forEach { it.testRunFinished(result) }
    }
}
