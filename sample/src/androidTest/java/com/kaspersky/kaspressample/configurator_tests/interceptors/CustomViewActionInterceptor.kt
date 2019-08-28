package com.kaspersky.kaspressample.configurator_tests.interceptors

import android.support.test.espresso.ViewAction
import android.view.View
import com.kaspersky.kaspressample.configurator_tests.helpers.CheckCustomInterceptorsStorage
import com.kaspersky.kaspresso.interceptors.view.ViewActionInterceptor

class CustomViewActionInterceptor : ViewActionInterceptor {

    override fun intercept(viewAction: ViewAction, view: View) {
        CheckCustomInterceptorsStorage.putToViewActionInterceptorCheckList()
    }
}