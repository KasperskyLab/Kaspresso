package com.kaspersky.kaspresso.testcases.step

abstract class StagesStepInterceptor : StepInterceptor {

    abstract fun beforeStep(chain: StepInterceptor.Chain)

    final override fun intercept(chain: StepInterceptor.Chain) {
        beforeStep(chain)

        var error: Throwable? = null
        try {
            chain.proceed(chain.action)
        } catch (e: Throwable) {
            error = e
        }

        if (error == null) {
            afterStepWithSuccess(chain)

        } else {
            afterStepWithError(chain, error)
            throw error
        }
    }

    abstract fun afterStepWithSuccess(chain: StepInterceptor.Chain)

    abstract fun afterStepWithError(chain: StepInterceptor.Chain, error: Throwable)
}