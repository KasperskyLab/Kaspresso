package com.kaspersky.kaspresso.runner

import android.os.Bundle
import androidx.test.runner.AndroidJUnitRunner
import com.kaspersky.kaspresso.runner.listener.KaspressoRunNotifier
import com.kaspersky.kaspresso.runner.listener.KaspressoRunNotifierImpl
import com.kaspersky.kaspresso.runner.listener.SpyRunListener

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

@Suppress("UNUSED")
open class KaspressoRunner : AndroidJUnitRunner() {

    val runNotifier: KaspressoRunNotifier = KaspressoRunNotifierImpl()

    override fun onCreate(arguments: Bundle) {
        // Listeners have to be set through extras https://developer.android.com/reference/androidx/test/runner/AndroidJUnitRunner
        arguments.putArgs("listener", SpyRunListener::class.java.name)
        super.onCreate(arguments)
    }

    private fun Bundle.putArgs(key: String, vararg values: CharSequence?) {
        val valuesArg: String = listOfNotNull(
            getCharSequence(key),
            *values
        ).joinToString(separator = ",")
        putCharSequence(key, valuesArg)
    }
}
