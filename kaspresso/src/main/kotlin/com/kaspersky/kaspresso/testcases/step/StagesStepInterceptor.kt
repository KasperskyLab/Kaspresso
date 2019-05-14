package com.kaspersky.kaspresso.testcases.step

abstract class StagesStepInterceptor : StepInterceptor {

    abstract fun beforeStep(chain: StepInterceptor.Chain)

    final override fun intercept(chain: StepInterceptor.Chain) {
        beforeStep(chain)

        try {
            chain.proceed(chain.action)
            afterStepWithSuccess(chain)
        } catch (throwable: Throwable) {
            afterStepWithError(chain, throwable)
            throw throwable
        }
    }

    abstract fun afterStepWithSuccess(chain: StepInterceptor.Chain)

    abstract fun afterStepWithError(chain: StepInterceptor.Chain, error: Throwable)
}