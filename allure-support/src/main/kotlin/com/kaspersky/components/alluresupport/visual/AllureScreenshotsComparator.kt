package com.kaspersky.components.alluresupport.visual

import android.graphics.Bitmap
import com.kaspersky.components.alluresupport.files.attachScreenshotToAllureReport
import com.kaspersky.kaspresso.files.resources.ResourceFileNamesProvider
import com.kaspersky.kaspresso.files.resources.ResourcesDirsProvider
import com.kaspersky.kaspresso.files.resources.ResourcesRootDirsProvider
import com.kaspersky.kaspresso.internal.visual.DefaultScreenshotsComparator
import com.kaspersky.kaspresso.logger.Logger
import com.kaspersky.kaspresso.visual.VisualTestParams
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

class AllureScreenshotsComparator(
    visualTestParams: VisualTestParams,
    logger: Logger,
    resourcesRootDirsProvider: ResourcesRootDirsProvider,
    resourcesDirsProvider: ResourcesDirsProvider,
    resourceFileNamesProvider: ResourceFileNamesProvider,
) : DefaultScreenshotsComparator(visualTestParams, logger, resourcesRootDirsProvider, resourcesDirsProvider, resourceFileNamesProvider) {
    override fun compare(originalScreenshot: File, newScreenshot: File): Boolean {
        val doScreenshotsMatch = super.compare(originalScreenshot, newScreenshot)
        if (!doScreenshotsMatch) {
            originalScreenshot.attachScreenshotToAllureReport()
            newScreenshot.attachScreenshotToAllureReport()
        }

        return doScreenshotsMatch
    }

    override fun processScreenshotDiff(original: Bitmap, diffPixels: IntArray, diffName: String): File {
        return super.processScreenshotDiff(original, diffPixels, diffName).also {
            it.attachScreenshotToAllureReport()
        }
    }
}
