package com.kaspersky.kaspresso.upgradesample.common

import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext

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

object UpdateManager {

    private const val MAIN_APP_PACKAGE_ID = "com.kaspersky.kaspressample"
    private const val OLD_VERSION_FILE = "artifacts/upgrade_test_v1.apk"
    private const val NEW_VERSION_FILE = "artifacts/upgrade_test_v2.apk"
    private const val TIMEOUT = 15_000L

    fun BaseTestContext.installAndLaunchMainApp() {
        device.apps.install(OLD_VERSION_FILE)

        with(device.targetContext) {
            val intent = packageManager.getLaunchIntentForPackage(MAIN_APP_PACKAGE_ID)
            startActivity(intent)
        }

        Thread.sleep(TIMEOUT)
    }

    fun BaseTestContext.updateAndLaunchMainApp() {
        adbServer.performAdb("install -r $NEW_VERSION_FILE")

        with(device.targetContext) {
            val intent = packageManager.getLaunchIntentForPackage(MAIN_APP_PACKAGE_ID)
            startActivity(intent)
        }

        Thread.sleep(TIMEOUT)
    }

    fun BaseTestContext.uninstallMainApp() {
        device.apps.uninstallIfExists(MAIN_APP_PACKAGE_ID)
    }
}
