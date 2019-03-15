package com.kaspersky.uitest_framework.interceptors

import android.view.View
import android.support.test.espresso.ViewAction

interface ViewActionInterceptor {

    fun intercept(viewAction: ViewAction, view: View)
}