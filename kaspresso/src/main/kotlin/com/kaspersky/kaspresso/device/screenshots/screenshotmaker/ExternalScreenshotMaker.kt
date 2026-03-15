package com.kaspersky.kaspresso.device.screenshots.screenshotmaker

import androidx.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.instrumental.InstrumentalDependencyProvider
import com.kaspersky.kaspresso.params.ScreenshotParams
import java.io.File

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
 * Captures spoon-compatible screenshots by uiautomator.
 */
class ExternalScreenshotMaker(
    private val instrumentalDependencyProvider: InstrumentalDependencyProvider,
    private val params: ScreenshotParams = ScreenshotParams()
) : ScreenshotMaker {

    private val device: UiDevice
        get() = instrumentalDependencyProvider.uiDevice

    // Somehow scale param is not used in UiDevice#takeScreenshot method,
    // so just using default here
    private val scale: Float = 1.0f

    override fun takeScreenshot(file: File) {
        device.takeScreenshot(file, scale, params.quality)
    }

    override fun takeFullWindowScreenshot(file: File) {
        TODO("External Full Window Screenshot not yet implemented")
    }
}
