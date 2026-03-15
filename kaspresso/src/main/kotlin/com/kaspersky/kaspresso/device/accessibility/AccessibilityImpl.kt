package com.kaspersky.kaspresso.device.accessibility

import android.annotation.TargetApi
import android.app.UiAutomation
import android.os.Build
import androidx.test.uiautomator.Configurator
import com.kaspersky.kaspresso.instrumental.InstrumentalDependencyProvider
import com.kaspersky.kaspresso.logger.UiTestLogger

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

/**
 * The implementation of the [Accessibility] interface.
 */
class AccessibilityImpl(
    private val instrumentalDependencyProvider: InstrumentalDependencyProvider,
    private val logger: UiTestLogger
) : Accessibility {

    /**
     * Enables accessibility. Available since api 24.
     *
     * @param packageName a package name of an accessibility service.
     * @param className a class name of an accessibility service.
     */
    @TargetApi(Build.VERSION_CODES.N)
    override fun enable(packageName: String, className: String) {
        val string = "enabled_accessibility_services"
        val cmd = "settings put secure $string $packageName/$className"

        val flags = UiAutomation.FLAG_DONT_SUPPRESS_ACCESSIBILITY_SERVICES
        Configurator.getInstance().uiAutomationFlags = flags

        instrumentalDependencyProvider
            .getUiAutomation(UiAutomation.FLAG_DONT_SUPPRESS_ACCESSIBILITY_SERVICES)
            .executeShellCommand(cmd)
            .close()

        logger.i("Accessibility service $packageName.$className enabled")
    }

    /**
     * Disables accessibility. Available since api 24.
     */
    @TargetApi(Build.VERSION_CODES.N)
    override fun disable() {
        val string = "enabled_accessibility_services"
        val cmd = "settings put secure $string null"

        instrumentalDependencyProvider
            .getUiAutomation(UiAutomation.FLAG_DONT_SUPPRESS_ACCESSIBILITY_SERVICES)
            .executeShellCommand(cmd)
            .close()

        logger.i("Accessibility services disabled")
    }
}
