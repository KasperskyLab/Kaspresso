package com.kaspersky.components.allure_support

import com.kaspersky.components.allure_support.interceptors.step.AllureMapperStepInterceptor
import com.kaspersky.components.allure_support.interceptors.step.ScreenshotStepInterceptor
import com.kaspersky.components.allure_support.interceptors.testrun.DumpLogcatTestInterceptor
import com.kaspersky.components.allure_support.interceptors.testrun.DumpViewsTestInterceptor
import com.kaspersky.components.allure_support.interceptors.testrun.ScreenshotTestInterceptor
import com.kaspersky.components.allure_support.interceptors.testrun.VideoRecordingTestInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso

fun Kaspresso.Builder.withAllureSupport(): Kaspresso.Builder = apply {
    stepWatcherInterceptors.addAll(
        listOf(
            ScreenshotStepInterceptor(screenshots),
            AllureMapperStepInterceptor()
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
