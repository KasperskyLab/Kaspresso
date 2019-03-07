package com.kaspersky.uitest_framework.kakao.interceptors

import android.support.test.espresso.ViewAction
import android.view.View

interface ViewActionInterceptor {

    fun intercept(viewAction: ViewAction, view: View)
}