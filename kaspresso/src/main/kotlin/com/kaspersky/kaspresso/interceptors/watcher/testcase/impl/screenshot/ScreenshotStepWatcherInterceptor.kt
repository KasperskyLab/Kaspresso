package com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.screenshot

import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.interceptors.watcher.testcase.StepWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.StepInfo

class ScreenshotStepWatcherInterceptor(
    private val screenshots: Screenshots
) : StepWatcherInterceptor {

    override fun interceptAfterWithSuccess(stepInfo: StepInfo) {
        screenshots.take(makeScreenshotTag(stepInfo))
    }

    override fun interceptAfterWithError(stepInfo: StepInfo, error: Throwable) {
        screenshots.take("${makeScreenshotTag(stepInfo)}_failure_${error.javaClass.simpleName}")
    }

    private fun makeScreenshotTag(stepInfo: StepInfo) = "${stepInfo.testClassName}_step_${stepInfo.ordinal}"
}