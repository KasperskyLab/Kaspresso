package com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging

import com.kaspersky.kaspresso.interceptors.watcher.testcase.StepWatcherInterceptor
import com.kaspersky.kaspresso.internal.extensions.other.toTime
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.testcases.models.info.StepInfo

/**
 * The implementation of the [StepWatcherInterceptor] interface.
 * Logs [StepInfo] on each step event.
 */
class LoggingStepWatcherInterceptor(
    private val logger: UiTestLogger
) : StepWatcherInterceptor {

    private val stepStartMap: MutableMap<StepInfo, Long> = mutableMapOf()

    /**
     * Logs the given [stepInfo] on step starts.
     *
     * @param stepInfo the step info to log.
     */
    override fun interceptBefore(stepInfo: StepInfo) {
        startStepTimer(stepInfo)
        logger.header(getStepHeader(stepInfo))
    }

    /**
     * Logs the given [stepInfo] on step finishes with success.
     *
     * @param stepInfo the step info to log.
     */
    override fun interceptAfterWithSuccess(stepInfo: StepInfo) {
        val stepTime = stopStepTimerAndGetTime(stepInfo)
        logger.i("${getStepHeader(stepInfo)} SUCCEED. ${getTimeReport(stepTime)} ")
    }

    /**
     * Logs the given [stepInfo] on step finishes with failure.
     *
     * @param stepInfo the step info to log.
     * @param error the error occurred to log.
     */
    override fun interceptAfterWithError(stepInfo: StepInfo, error: Throwable) {
        val stepTime = stopStepTimerAndGetTime(stepInfo)
        logger.i("${getStepHeader(stepInfo)} FAILED. ${getTimeReport(stepTime)} ")
    }

    /**
     * Logs the given [stepInfo] on step finally finishes.
     *
     * @param stepInfo the step info to log.
     */
    override fun interceptAfterFinally(stepInfo: StepInfo) {
        logger.line()
    }

    private fun startStepTimer(stepInfo: StepInfo) {
        stepStartMap[stepInfo] = System.currentTimeMillis()
    }

    private fun stopStepTimerAndGetTime(stepInfo: StepInfo): Triple<Long, Long, Long> {
        val stepStartTime: Long? = stepStartMap.remove(stepInfo)

        stepStartTime ?: throw AssertionError("Step start timestamp was already removed")

        return (System.currentTimeMillis() - stepStartTime).toTime()
    }

    private fun getTimeReport(stepTime: Triple<Long, Long, Long>): String {
        val (minutes, secs, millis) = stepTime
        return "It took $minutes minutes, $secs seconds and $millis millis."
    }

    private fun getStepHeader(stepInfo: StepInfo): String {
        return "TEST STEP: \"" +
                (stepInfo.number?.let { "$it. " } ?: "") +
                "${stepInfo.description}\" in ${stepInfo.testClassName}"
    }
}
