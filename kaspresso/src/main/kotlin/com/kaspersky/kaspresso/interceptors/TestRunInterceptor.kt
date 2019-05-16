package com.kaspersky.kaspresso.interceptors

import com.kaspersky.kaspresso.testcases.TestInfo


interface TestRunInterceptor {

    fun onTestStarted(testInfo: TestInfo) = Unit

    fun onBeforeSectionStarted(testInfo: TestInfo) = Unit
    fun onBeforeSectionFinishedSuccess(testInfo: TestInfo) = Unit
    fun onBeforeSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) = Unit


    fun onMainSectionStarted(testInfo: TestInfo) = Unit
    fun onMainSectionFinishedSuccess(testInfo: TestInfo) = Unit
    fun onMainSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) = Unit


    fun onAfterSectionStarted(testInfo: TestInfo) = Unit
    fun onAfterSectionFinishedSuccess(testInfo: TestInfo) = Unit
    fun onAfterSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) = Unit

    fun onTestFinished(testInfo: TestInfo, success: Boolean) = Unit
}