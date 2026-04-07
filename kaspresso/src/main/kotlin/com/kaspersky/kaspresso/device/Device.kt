package com.kaspersky.kaspresso.device

import android.app.Instrumentation
import android.content.Context
import androidx.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.device.accessibility.Accessibility
import com.kaspersky.kaspresso.device.activities.Activities
import com.kaspersky.kaspresso.device.apps.Apps
import com.kaspersky.kaspresso.device.exploit.Exploit
import com.kaspersky.kaspresso.device.files.Files
import com.kaspersky.kaspresso.device.keyboard.Keyboard
import com.kaspersky.kaspresso.device.languages.Language
import com.kaspersky.kaspresso.device.location.Location
import com.kaspersky.kaspresso.device.logcat.Logcat
import com.kaspersky.kaspresso.device.network.Network
import com.kaspersky.kaspresso.device.permissions.HackPermissions
import com.kaspersky.kaspresso.device.permissions.Permissions
import com.kaspersky.kaspresso.device.phone.Phone
import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.instrumental.InstrumentalDependencyProvider

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
 * The provider of managers for all off-screen work.
 */
data class Device(

    /**
     * Holds the reference to the implementation of [Apps] interface.
     *
     * Required: Started AdbServer
     *     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"
     *     2. Start AdbServer => input in cmd "java jar path_to_file/adbserver-desktop.jar"
     */
    val apps: Apps,

    /**
     * Holds the reference to the implementation of [Activities] interface.
     */
    val activities: Activities,

    /**
     * Holds the reference to the implementation of [Files] interface.
     *
     * Required: Started AdbServer
     *     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"
     *     2. Start AdbServer => input in cmd "java jar path_to_file/adbserver-desktop.jar"
     */
    val files: Files,

    /**
     * Holds the reference to the implementation of [Network] interface.
     *
     * Required: Started AdbServer
     *     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"
     *     2. Start AdbServer => input in cmd "java jar path_to_file/adbserver-desktop.jar"
     */
    val network: Network,

    /**
     * Holds the reference to the implementation of [Phone] interface.
     *
     * Required: Started AdbServer
     *     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"
     *     2. Start AdbServer => input in cmd "java jar path_to_file/adbserver-desktop.jar"
     */
    val phone: Phone,

    /**
     * Holds the reference to the implementation of [Location] interface.
     *
     * Required: Started AdbServer
     *     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"
     *     2. Start AdbServer => input in cmd "java jar path_to_file/adbserver-desktop.jar"
     */
    val location: Location,

    /**
     * Holds the reference to the implementation of [Keyboard] interface.
     *
     * Required: Started AdbServer
     *     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"
     *     2. Start AdbServer => input in cmd "java jar path_to_file/adbserver-desktop.jar"
     */
    val keyboard: Keyboard,

    /**
     * Holds the reference to the implementation of [Screenshots] interface.
     */
    val screenshots: Screenshots,

    /**
     * Holds the reference to the implementation of [Accessibility] interface.
     */
    val accessibility: Accessibility,

    /**
     * Holds the reference to the implementation of [Permissions] interface.
     */
    val permissions: Permissions,

    /**
     * Holds the reference to the implementation of [HackPermissions] interface.
     */
    val hackPermissions: HackPermissions,

    /**
     * Holds the reference to the implementation of [Exploit] interface.
     *
     * Required: Started AdbServer
     *     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"
     *     2. Start AdbServer => input in cmd "java jar path_to_file/adbserver-desktop.jar"
     */
    val exploit: Exploit,

    /**
     * Holds the reference to the implementation of [Language] interface.
     */
    val language: Language,

    /**
     * Holds the reference to the implementation of [Logcat] interface.
     */
    val logcat: Logcat,

    private val instrumentalDependencyProvider: InstrumentalDependencyProvider,

    private val instrumentation: Instrumentation
) {
    /**
     * A caching property to get [Context].
     */
    val context: Context = instrumentation.context

    /**
     * A caching property to get target [Context].
     */
    val targetContext: Context = instrumentation.targetContext

    /**
     * A property to get the instance of [UiDevice].
     */
    val uiDevice: UiDevice
        get() = instrumentalDependencyProvider.uiDevice
}
