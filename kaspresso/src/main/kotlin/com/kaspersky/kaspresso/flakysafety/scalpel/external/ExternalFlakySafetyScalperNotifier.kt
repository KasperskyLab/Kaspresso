package com.kaspersky.kaspresso.flakysafety.scalpel.external

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
