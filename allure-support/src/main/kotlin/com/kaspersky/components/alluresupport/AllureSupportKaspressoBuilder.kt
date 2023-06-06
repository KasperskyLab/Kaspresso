package com.kaspersky.components.alluresupport

import com.kaspersky.components.alluresupport.files.dirs.AllureDirsProvider
import com.kaspersky.components.alluresupport.files.resources.impl.AllureResourceFilesProvider
import com.kaspersky.components.alluresupport.files.resources.impl.DefaultAllureResourcesRootDirsProvider
import com.kaspersky.components.alluresupport.interceptors.step.AllureMapperStepInterceptor
import com.kaspersky.components.alluresupport.interceptors.step.ScreenshotStepInterceptor
import com.kaspersky.components.alluresupport.interceptors.testrun.DumpLogcatTestInterceptor
import com.kaspersky.components.alluresupport.interceptors.testrun.DumpViewsTestInterceptor
import com.kaspersky.components.alluresupport.interceptors.testrun.HackyVideoRecordingTestInterceptor
import com.kaspersky.components.alluresupport.interceptors.testrun.ScreenshotTestInterceptor
import com.kaspersky.components.alluresupport.interceptors.testrun.VideoRecordingTestInterceptor
import com.kaspersky.components.alluresupport.results.AllureResultsHack
import com.kaspersky.components.alluresupport.runlisteners.AllureRunListener
import com.kaspersky.kaspresso.files.dirs.DefaultDirsProvider
import com.kaspersky.kaspresso.files.resources.impl.DefaultResourceFileNamesProvider
import com.kaspersky.kaspresso.files.resources.impl.DefaultResourceFilesProvider
import com.kaspersky.kaspresso.files.resources.impl.DefaultResourcesDirNameProvider
import com.kaspersky.kaspresso.files.resources.impl.DefaultResourcesDirsProvider
import com.kaspersky.kaspresso.instrumental.InstrumentalDependencyProvider
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.runner.listener.addUniqueListener
import com.kaspersky.kaspresso.runner.listener.getUniqueListener

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
@Deprecated("This builder doesn't support storage system restrictions", ReplaceWith("Kaspresso.Builder.withForcedAllureSupport()"))
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

/**
 * Forces file providers needed for fixed allure support
 */
fun Kaspresso.Builder.Companion.withForcedAllureSupport(
    customize: Kaspresso.Builder.() -> Unit = {}
): Kaspresso.Builder = simple {
    if (!isAndroidRuntime) {
        return@simple
    }
    customize.invoke(this)
    val instrumentalDependencyProvider = instrumentalDependencyProviderFactory.getComponentProvider<Kaspresso>(instrumentation)
    forceAllureSupportFileProviders(instrumentalDependencyProvider)
    addRunListenersIfNeeded(instrumentalDependencyProvider)
}.apply(::addAllureSupportInterceptors)

private fun Kaspresso.Builder.forceAllureSupportFileProviders(provider: InstrumentalDependencyProvider) {
    resourcesDirNameProvider = DefaultResourcesDirNameProvider()
    resourceFileNamesProvider = DefaultResourceFileNamesProvider(addTimestamps = false)

    val allureResourcesRootDirsProvider = DefaultAllureResourcesRootDirsProvider()
    resourcesRootDirsProvider = allureResourcesRootDirsProvider

    val defaultDirsProvider = DefaultDirsProvider(provider)
    val allureDirsProvider = AllureDirsProvider(defaultDirsProvider, instrumentation, allureResourcesRootDirsProvider)
    dirsProvider = allureDirsProvider

    val defaultResourcesDirsProvider = DefaultResourcesDirsProvider(allureDirsProvider, resourcesDirNameProvider)
    resourcesDirsProvider = defaultResourcesDirsProvider

    val defaultResourceFilesProvider =
        DefaultResourceFilesProvider(allureResourcesRootDirsProvider, defaultResourcesDirsProvider, resourceFileNamesProvider)
    val allureResourcesFilesProvider =
        AllureResourceFilesProvider(defaultResourceFilesProvider, allureResourcesRootDirsProvider, resourcesDirsProvider)
    resourceFilesProvider = allureResourcesFilesProvider
}

private fun Kaspresso.Builder.addRunListenersIfNeeded(provider: InstrumentalDependencyProvider) {
    provider.runNotifier.apply {
        addUniqueListener(::AllureRunListener)
        addUniqueListener {
            AllureResultsHack(
                uiDevice = provider.uiDevice,
                resourcesRootDirsProvider = resourcesRootDirsProvider as DefaultAllureResourcesRootDirsProvider,
                dirsProvider = dirsProvider as AllureDirsProvider
            )
        }
    }
}

private fun addAllureSupportInterceptors(builder: Kaspresso.Builder): Unit = with(builder) {
    if (!isAndroidRuntime) {
        return@with
    }
    val provider = instrumentalDependencyProviderFactory.getComponentProvider<Kaspresso>(instrumentation)
    val allureResourcesFilesProvider = resourceFilesProvider as AllureResourceFilesProvider
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
            DumpViewsTestInterceptor(viewHierarchyDumper),
            HackyVideoRecordingTestInterceptor(videos, allureResourcesFilesProvider, provider.runNotifier.getUniqueListener())
        )
    )
}
