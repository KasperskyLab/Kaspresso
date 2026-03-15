package com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.screenshot

import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.interceptors.watcher.testcase.StepWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.StepInfo

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
 * The implementation of the [StepWatcherInterceptor] interface.
 * Takes screenshots if step succeeds or fails.
 */
class ScreenshotStepWatcherInterceptor(
    private val screenshots: Screenshots
) : StepWatcherInterceptor {

    /**
     * Takes a screenshot of the screen on which the step succeeded.
     *
     * @param stepInfo the step info to log.
     */
    override fun interceptAfterWithSuccess(stepInfo: StepInfo) {
        screenshots.take(makeTag(stepInfo))
    }

    /**
     * Takes a screenshot of the screen on which the step falied.
     *
     * @param stepInfo the step info to log.
     * @param error the error occurred to use in screenshots name.
     */
    override fun interceptAfterWithError(stepInfo: StepInfo, error: Throwable) {
        screenshots.take("${makeTag(stepInfo)}_failure_${error.javaClass.simpleName}")
    }

    private fun makeTag(stepInfo: StepInfo): String = "${stepInfo.testClassName}_step_${stepInfo.ordinal}"
}
