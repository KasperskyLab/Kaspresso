package com.kaspersky.kaspresso.testcases.step

class RealChainInterceptor(
    override val description: String,
    override val action: () -> Unit,
    override val testClassName: String,
    override val stepLevel: Int,
    override val stepOrderOnLevel: Int,
    override val ordinal: Int,
    private val index: Int,
    private val interceptors: List<StepInterceptor>
) : StepInterceptor.Chain {

    override fun proceed(action: () -> Unit) {
        if (index >= interceptors.size)
            throw AssertionError("index is $index, size is ${interceptors.size}")

        val next = RealChainInterceptor(
            description,
            action,
            testClassName,
            stepLevel,
            stepOrderOnLevel,
            ordinal,
            index + 1,
            interceptors
        )

        val interceptor = interceptors[index]
        interceptor.intercept(next)
    }
}