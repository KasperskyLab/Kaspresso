package com.kaspersky.uitest_framework.kakao.interceptors

object InterceptorsHolder {

    val viewActionInterceptors: ArrayList<ViewActionInterceptor> = arrayListOf()

    val viewAssertionInterceptors: ArrayList<ViewAssertionInterceptor> = arrayListOf()

    val atomInterceptors: ArrayList<AtomInterceptor> = arrayListOf()

    val webAssertionInterceptors: ArrayList<WebAssertionInterceptor> = arrayListOf()

    val failureHandlerInterceptors: ArrayList<FailureHandlerInterceptor> = arrayListOf()

    val matcherInterceptors: ArrayList<MatcherInterceptor> = arrayListOf()

    var executingInterceptor: ExecutingInterceptor? = null
}