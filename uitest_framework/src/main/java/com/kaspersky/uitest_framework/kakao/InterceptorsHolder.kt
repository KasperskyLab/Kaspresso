package com.kaspersky.uitest_framework.kakao

import com.kaspersky.uitest_framework.kakao.interceptors.*

/**
 * Created by egor.kurnikov on 06.03.2019
 */

object InterceptorsHolder {

    val viewActionInterceptors: ArrayList<ViewActionInterceptor> = arrayListOf()

    val viewAssertionInterceptors: ArrayList<ViewAssertionInterceptor> = arrayListOf()

    val failureHandlerInterceptors: ArrayList<FailureHandlerInterceptor> = arrayListOf()

    val matcherInterceptors: ArrayList<MatcherInterceptor> = arrayListOf()

    var executingInterceptor: ExecutingInterceptor? = null
}