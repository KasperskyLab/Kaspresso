package com.kaspersky.components.allure_support

import com.kaspersky.components.allure_support.interceptors.step.AllureMapperStepInterceptor
import com.kaspersky.components.allure_support.interceptors.step.ScreenshotStepInterceptor
import com.kaspersky.components.allure_support.interceptors.testrun.DumpLogcatTestInterceptor
import com.kaspersky.components.allure_support.interceptors.testrun.DumpViewsTestInterceptor
import com.kaspersky.components.allure_support.interceptors.testrun.ScreenshotTestInterceptor
import com.kaspersky.components.allure_support.interceptors.testrun.VideoRecordingTestInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso

fun withAllureSupport(customize: Kaspresso.Builder.() -> Unit = {}): Kaspresso.Builder =
    Kaspresso.Builder.simple(customize).apply {
        stepWatcherInterceptors.addAll(
            listOf(
                AllureMapperStepInterceptor(),
                ScreenshotStepInterceptor(screenshots)
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
