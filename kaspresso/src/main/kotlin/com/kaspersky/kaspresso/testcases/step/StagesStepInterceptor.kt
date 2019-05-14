package com.kaspersky.kaspresso.testcases.step

abstract class StagesStepInterceptor : StepInterceptor {
    
    final override fun intercept(chain: StepInterceptor.Chain) {
        beforeStep(chain)

        try {
            chain.proceed(chain.action)
            afterStepWithSuccess(chain)
        } catch (throwable: Throwable) {
            afterStepWithError(chain, throwable)
            throw throwable
        } finally {
            afterStep(chain)
        }
    }

    open fun afterStep(chain: StepInterceptor.Chain) = Unit

    open fun afterStepWithSuccess(chain: StepInterceptor.Chain) = Unit

    open fun afterStepWithError(chain: StepInterceptor.Chain, error: Throwable) = Unit

    open fun beforeStep(chain: StepInterceptor.Chain) = Unit
}