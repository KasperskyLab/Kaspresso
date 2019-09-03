package com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging

import com.kaspersky.kaspresso.extensions.other.toTime
import com.kaspersky.kaspresso.interceptors.watcher.testcase.StepWatcherInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.testcases.models.info.StepInfo

class LoggingStepWatcherInterceptor(
    private val logger: UiTestLogger
) : StepWatcherInterceptor {

    private val stepStartMap: MutableMap<StepInfo, Long> = mutableMapOf()

    override fun interceptBefore(stepInfo: StepInfo) {
        startStepTimer(stepInfo)
        logger.header(getStepHeader(stepInfo))
    }

    override fun interceptAfterWithSuccess(stepInfo: StepInfo) {
        val stepTime = stopStepTimerAndGetTime(stepInfo)
        logger.i("${getStepHeader(stepInfo)} SUCCEED. ${getTimeReport(stepTime)} ")
    }

    override fun interceptAfterWithError(stepInfo: StepInfo, error: Throwable) {
        val stepTime = stopStepTimerAndGetTime(stepInfo)
        logger.i("${getStepHeader(stepInfo)} FAILED. ${getTimeReport(stepTime)} ")
    }

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
        return "TEST STEP: \"${stepInfo.number}. ${stepInfo.description}\" in ${stepInfo.testClassName}"
    }
}