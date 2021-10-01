package com.kaspersky.components.alluresupport

import com.kaspersky.components.alluresupport.interceptors.step.AllureMapperStepInterceptor
import com.kaspersky.components.alluresupport.interceptors.step.ScreenshotStepInterceptor
import com.kaspersky.components.alluresupport.interceptors.testrun.DumpLogcatTestInterceptor
import com.kaspersky.components.alluresupport.interceptors.testrun.DumpViewsTestInterceptor
import com.kaspersky.components.alluresupport.interceptors.testrun.ScreenshotTestInterceptor
import com.kaspersky.components.alluresupport.interceptors.testrun.VideoRecordingTestInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso

fun Kaspresso.Builder.Companion.withAllureSupport(
    customize: Kaspresso.Builder.() -> Unit = {}
): Kaspresso.Builder = simple(customize).addAllureSupport()

fun Kaspresso.Builder.addAllureSupport(): Kaspresso.Builder = apply {
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
