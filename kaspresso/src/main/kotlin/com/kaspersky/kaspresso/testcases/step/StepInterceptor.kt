package com.kaspersky.kaspresso.testcases.step


interface StepInterceptor {

    fun intercept(chain: Chain)

    interface Chain {
        val action: () -> Unit

        val description: String

        val testClassName: String

        val stepLevel: Int

        val stepOrderOnLevel: Int

        val ordinal: Int

        fun proceed(action: () -> Unit)
    }
}