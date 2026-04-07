package com.kaspersky.components.alluresupport.interceptors.testrun

import com.kaspersky.components.alluresupport.files.resources.impl.AllureResourceFilesProvider
import com.kaspersky.components.alluresupport.results.AllureResultsHack
import com.kaspersky.kaspresso.device.video.Videos
import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

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
 * Due to screen recorder bug we have to perform a workaround which requires VideoRecordingTestInterceptor
 * and MoveReportsInterceptor to be the last two interceptors in allure reports. So if you use VideoRecordingTestInterceptor
 * be sure to use MoveReportsInterceptor too. Otherwise your report will be under /data/data/your.package.name/files/allure-results
 * and videos under /sdcard
 */
class HackyVideoRecordingTestInterceptor(
    private val videos: Videos,
    private val resourceFilesProvider: AllureResourceFilesProvider,
    private val hack: AllureResultsHack
) : TestRunWatcherInterceptor {

    override fun onTestStarted(testInfo: TestInfo) {
        videos.record("Video_${testInfo.testName}")
    }

    override fun onTestFinished(testInfo: TestInfo, success: Boolean) {
        videos.saveAndApply {
            hack.onVideoRecorded(resourceFilesProvider = resourceFilesProvider, videoFile = this)
        }
    }
}
