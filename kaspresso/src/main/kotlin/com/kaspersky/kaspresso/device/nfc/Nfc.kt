package com.kaspersky.kaspresso.device.nfc

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
 * The interface to control NFC adapter state on the device.
 *
 * **Important:** NFC is not supported by Android emulators. All methods in this interface
 * work only on physical devices that have NFC hardware.
 *
 * Typical use cases:
 * - Verify that the app shows the "NFC disabled" banner when NFC is off.
 * - Restore NFC state after a test that turns it off.
 *
 * Required: Started AdbServer
 *     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"
 *     2. Start AdbServer => input in cmd "java -jar path_to_file/adbserver-desktop.jar"
 */
interface Nfc {

    /**
     * Enables NFC on the device.
     *
     * Tries in order:
     * 1. `svc nfc enable` via AdbServer — works on most devices since API 17.
     * 2. Android Settings UI as a last resort (navigates to NFC settings screen and taps the toggle).
     *
     * **Note:** Works only on physical devices. Android emulators do not support NFC hardware.
     *
     * @throws RuntimeException if NFC hardware is not present on the device.
     */
    fun enable()

    /**
     * Disables NFC on the device.
     *
     * Tries in order:
     * 1. `svc nfc disable` via AdbServer — works on most devices since API 17.
     * 2. Android Settings UI as a last resort (navigates to NFC settings screen and taps the toggle).
     *
     * **Note:** Works only on physical devices. Android emulators do not support NFC hardware.
     *
     * @throws RuntimeException if NFC hardware is not present on the device.
     */
    fun disable()

    /**
     * Returns `true` if NFC is currently enabled on the device, `false` otherwise.
     *
     * Uses `settings get global nfc_adapter_state` under the hood:
     * - `3` → enabled
     * - `1` or anything else → disabled
     *
     * **Note:** Works only on physical devices. Android emulators do not support NFC hardware.
     *
     * @return `true` if NFC adapter is enabled, `false` if it is disabled or NFC hardware is absent.
     */
    fun isEnabled(): Boolean
}
