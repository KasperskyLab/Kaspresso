package com.kaspersky.kaspresso.testcases.step

import com.kaspersky.kaspresso.extensions.other.toTime
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.lang.AssertionError

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
        val msTook = System.currentTimeMillis() - provideStartTimestamp(chain)
        val (minutes, secs, millis) = msTook.toTime()

        logger.footer("TEST STEP: \"${chain.ordinal}. ${chain.description}\" in ${chain.testClassName} FAILED. It took $minutes minutes, $secs seconds and $millis millis.")
    }

    override fun afterStepWithSuccess(chain: StepInterceptor.Chain) {
        val msTook = System.currentTimeMillis() - provideStartTimestamp(chain)
        val (minutes, secs, millis) = msTook.toTime()

        logger.footer("TEST STEP: \"${chain.ordinal}. ${chain.description}\" in ${chain.testClassName} SUCCESS. It took $minutes minutes, $secs seconds and $millis millis.")
    }

    private fun provideStartTimestamp(chain: StepInterceptor.Chain) =
        stepStartMap.remove(chain) ?: throw AssertionError("Step start timestamp was already removed")
}