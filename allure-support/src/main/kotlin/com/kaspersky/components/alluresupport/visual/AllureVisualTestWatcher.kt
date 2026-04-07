package com.kaspersky.components.alluresupport.visual

import com.kaspersky.components.alluresupport.files.dirs.AllureDirsProvider
import com.kaspersky.kaspresso.device.files.Files
import com.kaspersky.kaspresso.files.resources.ResourcesRootDirsProvider
import com.kaspersky.kaspresso.logger.Logger
import com.kaspersky.kaspresso.visual.VisualTestParams
import com.kaspersky.kaspresso.visual.VisualTestType
import com.kaspersky.kaspresso.visual.VisualTestWatcher
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

class AllureVisualTestWatcher(
    private val params: VisualTestParams,
    private val logger: Logger,
    private val dirsProvider: AllureDirsProvider,
    private val resourcesRootDirsProvider: ResourcesRootDirsProvider,
    private val files: Files,
) : VisualTestWatcher {

    private val diffDir
        get() = dirsProvider.provideNew(resourcesRootDirsProvider.screenshotsDiffRootDir)
    private val originalScreenshotsTargetDir: File
        get() {
            val rootDir = dirsProvider.provideNewOnSdCard(File("")).absolutePath
            return File(rootDir, File(params.hostScreenshotsDir).name)
        }

    override fun prepare() {
        logger.i("Visual test run started. Parameters: $params")

        if (params.testType == VisualTestType.Compare) {
            logger.i("Pushing the screenshots unto the device...")
            dirsProvider.provideCleared(diffDir)

            // Allure stores all files in the app's private directory. We can't "adb push" directly there,
            // so we have to do this in 2 steps
            dirsProvider.provideCleared(originalScreenshotsTargetDir)
            val tmp = dirsProvider.provideNewOnSdCard(File(""))
            files.push(params.hostScreenshotsDir, tmp.absolutePath)
            val target = dirsProvider.provideNew(File("")).resolve(params.hostScreenshotsDir)
            File(tmp, params.hostScreenshotsDir).copyRecursively(target, overwrite = true)
            logger.i("Done pushing the screenshots unto the device")
        }
    }

    override fun cleanUp() {
        // Do nothing
    }
}
