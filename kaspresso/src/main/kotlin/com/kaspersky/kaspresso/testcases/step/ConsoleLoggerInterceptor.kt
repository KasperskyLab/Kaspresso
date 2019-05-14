package com.kaspersky.kaspresso.testcases.step

import com.kaspersky.kaspresso.extensions.other.toTime
import com.kaspersky.kaspresso.logger.UiTestLogger

class ConsoleLoggerInterceptor(private val logger: UiTestLogger) : StagesStepInterceptor() {

    private val stepStartMap: MutableMap<StepInterceptor.Chain, Long> = mutableMapOf()

    override fun beforeStep(chain: StepInterceptor.Chain) {
        stepStartMap[chain] = System.currentTimeMillis()
        logger.header("TEST STEP: \"${chain.ordinal}. ${chain.description}\" in ${chain.testClassName}")
        return

    }

    override fun afterStepWithError(
        chain: StepInterceptor.Chain,
        error: Throwable
    ) {
        val stepStartTime = stepStartMap.remove(chain) ?: 0L

        val msTook = System.currentTimeMillis() - stepStartTime
        val (minutes, secs, millis) = msTook.toTime()

        logger.footer("TEST STEP: \"${chain.ordinal}. ${chain.description}\" in ${chain.testClassName} FAILED. It took $minutes minutes, $secs seconds and $millis millis.")
    }

    override fun afterStepWithSuccess(chain: StepInterceptor.Chain) {
        val stepStartTime = stepStartMap.remove(chain) ?: 0L
        val msTook = System.currentTimeMillis() - stepStartTime
        val (minutes, secs, millis) = msTook.toTime()

        logger.footer("TEST STEP: \"${chain.ordinal}. ${chain.description}\" in ${chain.testClassName} SUCCESS. It took $minutes minutes, $secs seconds and $millis millis.")
    }
}