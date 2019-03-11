package com.kaspersky.uitest_framework.interceptors

object InterceptorsHolder {

    val viewActionInterceptors: ArrayList<ViewActionInterceptor> = arrayListOf()

    val viewAssertionInterceptors: ArrayList<ViewAssertionInterceptor> = arrayListOf()

    val atomInterceptors: ArrayList<AtomInterceptor> = arrayListOf()

    val webAssertionInterceptors: ArrayList<WebAssertionInterceptor> = arrayListOf()

    var executingInterceptor: ExecutingInterceptor? = null

    var failureInterceptor: FailureInterceptor? = null
}