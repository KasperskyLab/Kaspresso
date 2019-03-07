package com.kaspersky.uitest_framework.kakao.interceptors

import android.support.test.espresso.web.model.Evaluation

interface AtomInterceptor {

    fun intercept(evaluation: Evaluation?)
}