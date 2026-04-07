package com.kaspersky.kaspresso.files.resources.impl

import com.kaspersky.kaspresso.device.screenshots.screenshotfiles.ScreenshotDirectoryProvider
import com.kaspersky.kaspresso.files.models.TestMethod
import com.kaspersky.kaspresso.files.resources.ResourcesDirNameProvider

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
 * Special mapper between new and old systems of resource providing
 */
internal class SupportLegacyResourcesDirNameProvider(
    private val screenshotDirectoryProvider: ScreenshotDirectoryProvider,
) : ResourcesDirNameProvider {

    companion object {
        const val DEFAULT_RUN_NUMBER = 1
    }

    override fun provideResourcesDirName(testMethod: TestMethod): String {
        val relocatedTestMethod = com.kaspersky.kaspresso.device.screenshots.screenshotfiles.TestMethod(
            className = testMethod.className,
            methodName = testMethod.methodName
        )
        return screenshotDirectoryProvider.getDirectoryForTest(relocatedTestMethod, DEFAULT_RUN_NUMBER)
    }
}
