package com.kaspersky.kaspresso.internal.systemscreen

import com.kaspersky.components.kautomator.component.common.views.UiView
import com.kaspersky.components.kautomator.component.switch.UiSwitch
import com.kaspersky.components.kautomator.screen.UiScreen
import java.util.regex.Pattern

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

object NotificationsFullScreen : UiScreen<NotificationsFullScreen>() {

    override val packageName: String = "com.android.systemui"

    val mobileDataSwitch: UiView = UiView {
        withContentDescription(Pattern.compile(".*Mobile Phone.*"))
    }

    val bluetoothSwitch: UiSwitch = UiSwitch {
        withContentDescription(Pattern.compile(".*Bluetooth.*"))
    }
}
