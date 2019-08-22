package com.kaspersky.kaspressample.configurator.custom_configurator.interceptors

import com.kaspersky.kaspressample.configurator.custom_configurator.helpers.CheckCustomInterceptorsStorage
import com.kaspersky.kaspresso.interceptors.testcase.StepInterceptor
import com.kaspersky.kaspresso.testcases.models.info.StepInfo

class CustomStepInterceptor : StepInterceptor {

    override fun interceptAfterFinally(stepInfo: StepInfo) {
        CheckCustomInterceptorsStorage.putToStepInterceptorCheckList()
    }
}