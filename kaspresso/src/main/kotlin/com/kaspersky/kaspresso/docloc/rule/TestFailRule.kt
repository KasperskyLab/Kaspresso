package com.kaspersky.kaspresso.docloc.rule

import com.kaspersky.kaspresso.docloc.DocLocScreenshotCapturer
import org.junit.rules.TestWatcher
import org.junit.runner.Description

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
 * The test rule to capture a screenshot in case of unexpected docloc screenshot test failure.
 */
@Deprecated(
    "Current implementation isn't suited for doc loc tests error capturing because it's triggered after test is finished. It leads to capturing screenshots of home screen",
    replaceWith = ReplaceWith(
        """
    Kaspresso.Builder.simple().apply {
        testRunWatcherInterceptors.add(TestRunnerScreenshotWatcherInterceptor(screenshots))
    }
"""
    )
)
class TestFailRule internal constructor() : TestWatcher() {

    internal lateinit var screenshotCapturer: DocLocScreenshotCapturer

    override fun failed(throwable: Throwable?, description: Description?) {
        val screenshotName = "${description!!.className}_${description.methodName}".replace(".", "_")
        screenshotCapturer.captureScreenshotOnFail(screenshotName)
    }
}
