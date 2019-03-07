package com.kaspersky.uitest_framework.interceptors.flakysafety

import com.kaspersky.uitest_framework.attempting.attempt
import com.kaspersky.uitest_framework.kakao.interceptors.ExecutingInterceptor

class FlakySafeExecutingInterceptor: ExecutingInterceptor {

    override fun interceptAndExecute(function: () -> Unit) = attempt { function.invoke() }
}