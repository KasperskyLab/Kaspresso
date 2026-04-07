package com.kaspersky.kaspresso.instrumental

import android.app.Instrumentation
import android.app.UiAutomation
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.test.uiautomator.UiDevice
import com.kaspersky.components.kautomator.common.Environment
import com.kaspersky.components.kautomator.common.environment
import com.kaspersky.kaspresso.instrumental.exception.NotSupportedInstrumentalTestException
import com.kaspersky.kaspresso.runner.listener.KaspressoRunNotifier
import com.kaspersky.kaspresso.runner.listener.getKaspressoRunNotifier

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

class InstrumentalDependencyProviderImpl(
    private val location: InstrumentalUsage,
    private val instrumentation: Instrumentation
) : InstrumentalDependencyProvider {

    override val isAndroidRuntime: Boolean =
        when (val environment = environment) {
            is Environment.AndroidRuntime -> true
            is Environment.Robolectric -> false
        }

    override val uiDevice: UiDevice
        get() =
            if (isAndroidRuntime) UiDevice.getInstance(instrumentation)
            else throw NotSupportedInstrumentalTestException(location, "UiDevice")

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getUiAutomation(flags: Int): UiAutomation {
        if (!isAndroidRuntime) throw NotSupportedInstrumentalTestException(location, "UiAutomation")
        return instrumentation.getUiAutomation(flags)
    }

    override val uiAutomation: UiAutomation
        get() =
            if (isAndroidRuntime) instrumentation.uiAutomation
            else throw NotSupportedInstrumentalTestException(location, "UiAutomation")

    override val runNotifier: KaspressoRunNotifier
        get() = instrumentation.getKaspressoRunNotifier()
}

sealed class InstrumentalUsage {
    data class ComponentLocation(val componentName: String) : InstrumentalUsage()
    data class InterceptorLocation(val interceptorName: String) : InstrumentalUsage()
    object TestLocation : InstrumentalUsage()
}
