package com.kaspersky.kaspressample.configurator_tests.interceptor_tests.interceptors

import com.kaspersky.kaspressample.configurator_tests.interceptor_tests.helpers.CheckCustomInterceptorsStorage
import com.kaspersky.kaspresso.interceptors.watcher.testcase.StepWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.StepInfo

class CustomStepWatcherInterceptor : StepWatcherInterceptor {

    override fun interceptAfterFinally(stepInfo: StepInfo) {
        CheckCustomInterceptorsStorage.putToStepInterceptorCheckList()
    }
}
