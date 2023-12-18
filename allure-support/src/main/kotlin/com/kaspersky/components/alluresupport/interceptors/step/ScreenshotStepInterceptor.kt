package com.kaspersky.components.alluresupport.interceptors.step

class ScreenshotStepInterceptor(
    private val screenshots: Screenshots
) : StepWatcherInterceptor {

    override fun interceptAfterWithSuccess(stepInfo: StepInfo) {
        intercept(makeTag(stepInfo))
    }

    override fun interceptAfterWithError(stepInfo: StepInfo, error: Throwable) {
        intercept("${makeTag(stepInfo)}_failure_${error.javaClass.simpleName}")
    }

    private fun intercept(tag: String) {
        screenshots.takeAndApply(tag) { attachScreenshotToAllureReport() }
    }

    private fun makeTag(stepInfo: StepInfo): String = "${stepInfo.testClassName}_step_${stepInfo.ordinal}"
}
