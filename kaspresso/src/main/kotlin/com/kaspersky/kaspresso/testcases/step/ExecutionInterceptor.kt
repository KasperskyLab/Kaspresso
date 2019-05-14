package com.kaspersky.kaspresso.testcases.step


class ExecutionInterceptor : StepInterceptor {
    override fun intercept(chain: StepInterceptor.Chain) {
        chain.action.invoke()
    }
}