package com.kaspersky.kaspresso.interceptors

import com.kaspersky.kaspresso.testcases.TestBody


interface TestRunInterceptor {

    fun onTestStarted(testBody: TestBody) = Unit

    fun onBeforeSectionStarted(testBody: TestBody) = Unit
    fun onBeforeSectionFinishedSuccess(testBody: TestBody) = Unit
    fun onBeforeSectionFinishedFailed(testBody: TestBody, throwable: Throwable) = Unit


    fun onMainSectionStarted(testBody: TestBody) = Unit
    fun onMainSectionFinishedSuccess(testBody: TestBody) = Unit
    fun onMainSectionFinishedFailed(testBody: TestBody, throwable: Throwable) = Unit


    fun onAfterSectionStarted(testBody: TestBody) = Unit
    fun onAfterSectionFinishedSuccess(testBody: TestBody) = Unit
    fun onAfterSectionFinishedFailed(testBody: TestBody, throwable: Throwable) = Unit

    fun onTestFinished(testBody: TestBody,success: Boolean) = Unit
}