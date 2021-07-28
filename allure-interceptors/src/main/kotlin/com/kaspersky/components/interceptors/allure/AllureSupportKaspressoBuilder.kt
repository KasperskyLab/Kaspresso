package com.kaspersky.components.interceptors.allure

import com.kaspersky.components.interceptors.allure.interceptors.step.AllureMapperStepInterceptor
import com.kaspersky.components.interceptors.allure.interceptors.step.ScreenshotStepInterceptor
import com.kaspersky.components.interceptors.allure.interceptors.testrun.DumpLogcatTestInterceptor
import com.kaspersky.components.interceptors.allure.interceptors.testrun.DumpViewsTestInterceptor
import com.kaspersky.components.interceptors.allure.interceptors.testrun.ScreenshotTestInterceptor
import com.kaspersky.components.interceptors.allure.interceptors.testrun.VideoRecordingTestInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso

fun Kaspresso.Builder.withAllureSupport(): Kaspresso.Builder = this.apply {
    stepWatcherInterceptors.addAll(
        // The order matters here. AllureMapperStepInterceptor should be the last one.
        listOf(
            ScreenshotStepInterceptor(screenshots),
            AllureMapperStepInterceptor(),
        )
    )
    testRunWatcherInterceptors.addAll(
        listOf(
            ScreenshotTestInterceptor(screenshots),
            VideoRecordingTestInterceptor(videos),
            DumpLogcatTestInterceptor(logcatDumper),
            DumpViewsTestInterceptor(viewHierarchyDumper)
        )
    )
}
