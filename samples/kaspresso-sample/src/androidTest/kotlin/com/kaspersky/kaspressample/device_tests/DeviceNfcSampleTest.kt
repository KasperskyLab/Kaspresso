package com.kaspersky.kaspressample.device_tests

import android.nfc.NfcAdapter
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Assume.assumeNotNull
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

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
 * Sample test demonstrating [com.kaspersky.kaspresso.device.nfc.Nfc] device API.
 *
 * **Important:** NFC is not supported by Android emulators. This test must be run
 * on a physical device that has NFC hardware. On a device without NFC the test is
 * skipped automatically via [assumeNotNull].
 *
 * Requires AdbServer to be running:
 * ```
 * java -jar artifacts/adbserver-desktop.jar
 * ```
 */
@Ignore("Works only on physical devices with NFC hardware. Run manually on a real device.")
class DeviceNfcSampleTest : TestCase() {

    companion object {
        private const val NFC_TOGGLE_DELAY_MS = 1_000L
    }

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun nfcSampleTest() {
        before {
            // Skip the test automatically on devices without NFC hardware (e.g. emulators)
            assumeNotNull(NfcAdapter.getDefaultAdapter(device.targetContext))

            device.nfc.enable()
        }.after {
            // Restore NFC to enabled state so the device is left in a clean condition
            if (NfcAdapter.getDefaultAdapter(device.targetContext) != null) {
                device.nfc.enable()
            }
        }.run {

            step("Check NFC hardware is present") {
                val adapter = NfcAdapter.getDefaultAdapter(device.targetContext)
                assertNotNull("NFC hardware not found — run this test on a physical device", adapter)
            }

            step("Disable NFC and verify") {
                device.nfc.disable()
                flakySafely(timeoutMs = NFC_TOGGLE_DELAY_MS) {
                    assertFalse("NFC should be disabled", device.nfc.isEnabled())
                }
            }

            step("Enable NFC and verify") {
                device.nfc.enable()
                flakySafely(timeoutMs = NFC_TOGGLE_DELAY_MS) {
                    assertTrue("NFC should be enabled", device.nfc.isEnabled())
                }
            }
        }
    }
}
