package com.kaspersky.components.alluresupport

import com.kaspersky.components.alluresupport.interceptors.step.AllureMapperStepInterceptor
import com.kaspersky.components.alluresupport.interceptors.step.ScreenshotStepInterceptor
import com.kaspersky.components.alluresupport.interceptors.testrun.DumpLogcatTestInterceptor
import com.kaspersky.components.alluresupport.interceptors.testrun.DumpViewsTestInterceptor
import com.kaspersky.components.alluresupport.interceptors.testrun.ScreenshotTestInterceptor
import com.kaspersky.components.alluresupport.interceptors.testrun.VideoRecordingTestInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso

/**
 * Kaspresso Builder that includes all appropriate interceptors to support rich Allure reports.
 *
 * If a test is executing on the JVM (with Robolectric) environment then mentioned above interceptors are not including to prevent crashes.
 * Allure reports don't have any sense in non Instrumental environment.
 */
fun Kaspresso.Builder.Companion.withAllureSupport(
    customize: Kaspresso.Builder.() -> Unit = {}
): Kaspresso.Builder = simple(customize).addAllureSupport()

/**
 * Kaspresso Builder that includes all appropriate interceptors to support rich Allure reports.
 *
 * If a test is executing on the JVM (with Robolectric) environment then mentioned above interceptors are not including to prevent crashes.
 * Allure reports don't have any sense in non Instrumental environment.
 */
fun Kaspresso.Builder.addAllureSupport(): Kaspresso.Builder = apply {
    if (isAndroidRuntime) {
        stepWatcherInterceptors.addAll(
            listOf(
                ScreenshotStepInterceptor(screenshots),
                AllureMapperStepInterceptor()
            )
        )
        testRunWatcherInterceptors.addAll(
            listOf(
                DumpLogcatTestInterceptor(logcatDumper),
                ScreenshotTestInterceptor(screenshots),
                VideoRecordingTestInterceptor(videos),
                DumpViewsTestInterceptor(viewHierarchyDumper)
            )
        )
    }
}
