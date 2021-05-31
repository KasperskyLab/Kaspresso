package com.kaspersky.kaspressample.configurator_tests.interceptor_tests.interceptors

import android.view.View
import androidx.test.espresso.ViewAction
import com.kaspersky.kaspressample.configurator_tests.interceptor_tests.helpers.CheckCustomInterceptorsStorage
import com.kaspersky.kaspresso.interceptors.watcher.view.ViewActionWatcherInterceptor

class CustomViewActionWatcherInterceptor : ViewActionWatcherInterceptor {

    override fun intercept(viewAction: ViewAction, view: View) {
        CheckCustomInterceptorsStorage.putToViewActionInterceptorCheckList()
    }
}
