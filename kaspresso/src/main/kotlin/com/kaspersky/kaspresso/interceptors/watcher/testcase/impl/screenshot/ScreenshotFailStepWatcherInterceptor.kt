package com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.screenshot

import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.interceptors.watcher.testcase.StepWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.StepInfo

class ScreenshotFailStepWatcherInterceptor(
    private val screenshots: Screenshots
) : StepWatcherInterceptor {
    /**
     * Takes a screenshot of the screen on which the step failed.
     *
     * @param stepInfo the step info to log.
     * @param error the error occurred to use in screenshots name.
     */
    override fun interceptAfterWithError(stepInfo: StepInfo, error: Throwable) {
        screenshots.take("${makeTag(stepInfo)}_failure_${error.javaClass.simpleName}")
    }

    private fun makeTag(stepInfo: StepInfo): String = "${stepInfo.testClassName}_step_${stepInfo.ordinal}"
}
