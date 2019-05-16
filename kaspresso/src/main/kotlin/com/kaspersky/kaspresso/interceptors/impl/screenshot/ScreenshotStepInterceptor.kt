package com.kaspersky.kaspresso.interceptors.impl.screenshot

import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.interceptors.StepInterceptor
import com.kaspersky.kaspresso.testcases.StepInfo

class ScreenshotStepInterceptor(
    private val screenshots: Screenshots
) : StepInterceptor {

    override fun interceptAfterWithSuccess(stepInfo: StepInfo) {
        screenshots.makeIfPossible(makeScreenshotTag(stepInfo))
    }

    override fun interceptAfterWithError(stepInfo: StepInfo, error: Throwable) {
        screenshots.makeIfPossible("${makeScreenshotTag(stepInfo)}_failure_${error.javaClass.simpleName}")
    }

    private fun makeScreenshotTag(stepInfo: StepInfo) = "${stepInfo.testClassName}_step_${stepInfo.ordinal}"
}