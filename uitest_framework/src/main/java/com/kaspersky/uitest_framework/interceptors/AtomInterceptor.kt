package com.kaspersky.uitest_framework.interceptors

import android.support.test.espresso.web.model.Evaluation

interface AtomInterceptor {

    fun intercept(evaluation: Evaluation?)
}