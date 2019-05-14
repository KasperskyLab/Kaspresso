package com.kaspersky.kaspresso.testcases.step

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.device.screenshots.Screenshots

class ScreenshotInterceptor(
    private val screenshots: Screenshots = Configurator.screenshots
) : StagesStepInterceptor() {


    override fun beforeStep(chain: StepInterceptor.Chain) = Unit

    override fun afterStepWithSuccess(chain: StepInterceptor.Chain) {
        screenshots.makeIfPossible(makeScreenshotTag(chain))
    }

    override fun afterStepWithError(chain: StepInterceptor.Chain, error: Throwable) {
        screenshots.makeIfPossible("${makeScreenshotTag(chain)}_failure_${error.javaClass.simpleName}")
    }

    private fun makeScreenshotTag(chain: StepInterceptor.Chain) = "${chain.testClassName}_step_${chain.ordinal}"
}
