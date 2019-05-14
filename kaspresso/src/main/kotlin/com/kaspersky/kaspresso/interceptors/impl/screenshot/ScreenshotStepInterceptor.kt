package com.kaspersky.kaspresso.interceptors.impl.screenshot

import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.interceptors.StepInterceptor
import com.kaspersky.kaspresso.testcases.Step

class ScreenshotStepInterceptor(
    private val screenshots: Screenshots
) : StepInterceptor {

    override fun interceptBefore(step: Step) = Unit

    override fun interceptAfterWithSuccess(step: Step) {
        screenshots.makeIfPossible(makeScreenshotTag(step))
    }

    override fun interceptAfterWithError(step: Step, error: Throwable) {
        screenshots.makeIfPossible("${makeScreenshotTag(step)}_failure_${error.javaClass.simpleName}")
    }

    private fun makeScreenshotTag(step: Step) = "${step.testClassName}_step_${step.ordinal}"
}
