package com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.screenshot

import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.interceptors.watcher.testcase.StepWatcherInterceptor
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import com.kaspersky.kaspresso.testcases.models.info.StepInfo

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
    override fun BaseTestContext.interceptAfterWithSuccess(stepInfo: StepInfo) {
        screenshots.take(makeScreenshotTag(stepInfo))
    }

    /**
     * Takes a screenshot of the screen on which the step falied.
     *
     * @param stepInfo the step info to log.
     * @param error the error occurred to use in screenshots name.
     */
    override fun BaseTestContext.interceptAfterWithError(stepInfo: StepInfo, error: Throwable) {
        screenshots.take("${makeScreenshotTag(stepInfo)}_failure_${error.javaClass.simpleName}")
    }

    private fun makeScreenshotTag(stepInfo: StepInfo) = "${stepInfo.testClassName}_step_${stepInfo.ordinal}"
}