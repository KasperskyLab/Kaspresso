package com.kaspersky.kaspresso.docloc

import com.kaspersky.kaspresso.device.screenshots.screenshotmaker.ScreenshotMaker
import com.kaspersky.kaspresso.docloc.metadata.saver.MetadataSaver
import com.kaspersky.kaspresso.files.resources.ResourceFilesProvider
import com.kaspersky.kaspresso.internal.wait.wait
import com.kaspersky.kaspresso.logger.UiTestLogger

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
 * The class for saving and clearing docloc screenshots with metadata.
 * @param screenshotRootDir directory to save screenshots and metadata.
 */
internal class DocLocScreenshotCapturer(
    private val logger: UiTestLogger,
    private val resourceFilesProvider: ResourceFilesProvider,
    private val screenshotMaker: ScreenshotMaker,
    private val metadataSaver: MetadataSaver
) {
    private companion object {
        private const val SCREENSHOT_CAPTURE_DELAY_MS: Long = 500
        private const val SCREENSHOT_DEFAULT_FAILS_SUB_DIR = "fails"
    }

    /**
     * Captures a screenshot and save it with metadata to [screenshotRootDir].
     * @param screenshotName name of screenshot. Must match [a-zA-Z0-9_-]+
     */
    fun captureScreenshot(screenshotName: String) {
        wait(timeoutMs = SCREENSHOT_CAPTURE_DELAY_MS, logger = logger) {
            val screenshotFile = resourceFilesProvider.provideScreenshotFile(screenshotName)
            screenshotMaker.takeScreenshot(screenshotFile)
            metadataSaver.saveScreenshotMetadata(screenshotFile.parentFile, screenshotName)
        }
    }

    /**
     * Captures a screenshot and save it with metadata to [screenshotRootDir].
     * @param screenshotName name of screenshot. Must match [a-zA-Z0-9_-]+
     */
    fun captureFullWindowScreenshot(screenshotName: String) {
        wait(timeoutMs = SCREENSHOT_CAPTURE_DELAY_MS, logger = logger) {
            val screenshotFile = resourceFilesProvider.provideScreenshotFile(screenshotName)
            screenshotMaker.takeFullWindowScreenshot(screenshotFile)
            metadataSaver.saveScreenshotMetadata(screenshotFile.parentFile, screenshotName)
        }
    }

    /**
     * Captures a screenshot and save it to [screenshotRootDir]/fails.
     * @param screenshotName name of screenshot. Must match [a-zA-Z0-9_-]+
     */
    fun captureScreenshotOnFail(screenshotName: String) {
        wait(timeoutMs = SCREENSHOT_CAPTURE_DELAY_MS, logger = logger) {
            val screenshotFile = resourceFilesProvider.provideScreenshotFile(screenshotName, SCREENSHOT_DEFAULT_FAILS_SUB_DIR)
            screenshotMaker.takeScreenshot(screenshotFile)
        }
    }
}
