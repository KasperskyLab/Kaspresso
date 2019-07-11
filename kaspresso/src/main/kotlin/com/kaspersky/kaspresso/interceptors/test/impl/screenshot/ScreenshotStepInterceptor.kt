package com.kaspersky.kaspresso.interceptors.test.impl.screenshot

import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.interceptors.test.StepInterceptor
import com.kaspersky.kaspresso.testcases.models.info.StepInfo

class ScreenshotStepInterceptor(
    private val screenshots: Screenshots
) : StepInterceptor {

    override fun interceptAfterWithSuccess(stepInfo: StepInfo) {
        screenshots.take(makeScreenshotTag(stepInfo))
    }

    override fun interceptAfterWithError(stepInfo: StepInfo, error: Throwable) {
        screenshots.take("${makeScreenshotTag(stepInfo)}_failure_${error.javaClass.simpleName}")
    }

    private fun makeScreenshotTag(stepInfo: StepInfo) = "${stepInfo.testClassName}_step_${stepInfo.ordinal}"
}