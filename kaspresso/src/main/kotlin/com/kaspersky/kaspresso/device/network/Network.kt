package com.kaspersky.kaspresso.device.network

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
 * The interface to work with network settings.
 *
 * Required: Started AdbServer
 *     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"
 *     2. Start AdbServer => input in cmd "java jar path_to_file/adbserver-desktop.jar"
 * Methods demanding to use AdbServer in the default implementation of this interface are marked.
 *     But nobody can't deprecate you to write implementation that doesn't require AdbServer.
 */
interface Network {

    /**
     * Enables wi-fi and mobile data using adb.
     */
    fun enable()

    /**
     * Disables wi-fi and mobile data using adb.
     */
    fun disable()

    /**
     * Toggles only mobile data. Note: it works only if airplane mode is off.
     */
    fun toggleMobileData(enable: Boolean)

    /**
     * Toggles only wi-fi. Note: it works only if airplane mode is off.
     */
    fun toggleWiFi(enable: Boolean)

    /**
     * Toggles airplane mode on or off.
     *
     * The implementation strategy depends on the Android API level:
     *
     * **API <= 23 (up to Android 6.0 Marshmallow):**
     * Uses `settings put global airplane_mode_on <0|1>` to update the global setting,
     * then fires an `android.intent.action.AIRPLANE_MODE` broadcast to notify the system.
     * This is the only reliable method on older devices, where `cmd connectivity` does not exist.
     *
     * **API >= 25 (Android 7.1 Nougat and above):**
     * Uses `adb shell cmd connectivity airplane-mode <enable|disable>`, which is the modern
     * and preferred way to toggle airplane mode programmatically without requiring root.
     * If this command fails (e.g. ADB server is unavailable), falls back to toggling the
     * switch via the Android Settings UI.
     *
     * **Known edge case on API 24 (Android 7.0 Nougat):**
     * API 24 is in an unfortunate middle ground: sending the `AIRPLANE_MODE` broadcast was
     * already restricted for third-party apps in this version, but `cmd connectivity airplane-mode`
     * had not been introduced yet. As a result, the ADB command path may fail silently on API 24,
     * and the implementation will fall back to toggling the setting via the Android Settings UI.
     *
     * @param enable `true` to enable airplane mode, `false` to disable it.
     */
    fun toggleAirplaneMode(enable: Boolean)
}
