package com.kaspersky.kaspresso.interceptors.impl.logging

import com.kaspersky.kaspresso.extensions.other.toTime
import com.kaspersky.kaspresso.interceptors.StepInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.testcases.Step

class LoggingStepInterceptor(
    private val logger: UiTestLogger
) : StepInterceptor {

    private val stepStartMap: MutableMap<Step, Long> = mutableMapOf()

    override fun interceptBefore(step: Step) {
        startStepTimer(step)
        logger.header(getStepHeader(step))
    }

    override fun interceptAfterWithSuccess(step: Step) {
        val stepTime = stopStepTimerAndGetTime(step)
        logger.i("${getStepHeader(step)} SUCCEED. ${getTimeReport(stepTime)} ")
    }

    override fun interceptAfterWithError(step: Step, error: Throwable) {
        val stepTime = stopStepTimerAndGetTime(step)
        logger.i("${getStepHeader(step)} FAILED. ${getTimeReport(stepTime)} ")
    }

    override fun interceptAfterFinally(step: Step) {
        logger.line()
    }

    private fun startStepTimer(step: Step) {
        stepStartMap[step] = System.currentTimeMillis()
    }

    private fun stopStepTimerAndGetTime(step: Step): Triple<Long, Long, Long> {
        val stepStartTime: Long? = stepStartMap.remove(step)

        stepStartTime ?: throw AssertionError("Step start timestamp was already removed")

        return (System.currentTimeMillis() - stepStartTime).toTime()
    }

    private fun getTimeReport(stepTime: Triple<Long, Long, Long>): String {
        val (minutes, secs, millis) = stepTime
        return "It took $minutes minutes, $secs seconds and $millis millis."
    }

    private fun getStepHeader(step: Step): String {
        return "TEST STEP: \"${step.ordinal}. ${step.description}\" in ${step.testClassName}"
    }
}