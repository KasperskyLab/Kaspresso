package com.kaspersky.kaspresso.internal.systemscreen

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.widget.Switch
import com.kaspersky.components.kautomator.component.switch.UiSwitch
import com.kaspersky.components.kautomator.screen.UiScreen

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

object DataUsageSettingsScreen : UiScreen<DataUsageSettingsScreen>() {

    private const val TIMEOUT = 5_000L

    override val packageName: String = "com.android.settings"

    val mobileDataSwitch: UiSwitch = UiSwitch {
        withClassName(Switch::class.java)
    }

    fun open(context: Context) {
        val intent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Intent(Settings.ACTION_DATA_USAGE_SETTINGS).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        } else Intent().apply {
            component = ComponentName(packageName, "com.android.settings.Settings\$DataUsageSummaryActivity")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
        waitForWindowUpdate(packageName, TIMEOUT)
    }

    fun close(context: Context) {
        pressBack()
        waitForWindowUpdate(context.packageName, TIMEOUT)
    }

    fun enableMobileData() {
        mobileDataSwitch.setChecked(true)
    }

    fun disableMobileData() {
        mobileDataSwitch.setChecked(false)
    }
}
