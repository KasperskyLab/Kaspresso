package com.kaspersky.components.composesupport

import com.kaspersky.components.composesupport.interceptors.behavior.SemanticsBehaviorInterceptor
import com.kaspersky.components.composesupport.interceptors.behavior.impl.autoscroll.AutoScrollSemanticsBehaviorInterceptor
import com.kaspersky.components.composesupport.interceptors.behavior.impl.elementloader.ElementLoaderSemanticsBehaviorInterceptor
import com.kaspersky.components.composesupport.interceptors.behavior.impl.failure.FailureLoggingSemanticsBehaviorInterceptor
import com.kaspersky.components.composesupport.interceptors.behavior.impl.flakysafety.FlakySafeSemanticsBehaviorInterceptor
import com.kaspersky.components.composesupport.interceptors.behavior.impl.systemsafety.SystemDialogSafetySemanticsBehaviorInterceptor
import com.kaspersky.components.composesupport.interceptors.watcher.SemanticsWatcherInterceptor
import com.kaspersky.components.composesupport.interceptors.watcher.impl.LoggingSemanticsWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety.SystemDialogSafetyViewBehaviorInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso

/**
 * Kaspresso Builder that includes all appropriate interceptors to support Jetpack Compose.
 */
fun Kaspresso.Builder.Companion.withComposeSupport(
    customize: Kaspresso.Builder.() -> Unit = {}
): Kaspresso.Builder = simple(customize).addComposeSupport()

/**
 * Kaspresso Builder that includes all appropriate interceptors to support Jetpack Compose.
 */
fun Kaspresso.Builder.addComposeSupport(): Kaspresso.Builder = apply {
    val semanticsWatcherInterceptors: List<SemanticsWatcherInterceptor> = listOf(
        LoggingSemanticsWatcherInterceptor(libLogger)
    )

    val semanticsBehaviorInterceptors: List<SemanticsBehaviorInterceptor> =
        if (isAndroidRuntime) {
            listOf(
                AutoScrollSemanticsBehaviorInterceptor(libLogger, autoScrollParams),
                SystemDialogSafetySemanticsBehaviorInterceptor(
                    libLogger,
                    instrumentalDependencyProviderFactory.getInterceptorProvider<SystemDialogSafetySemanticsBehaviorInterceptor>(instrumentation),
                    adbServer
                ),
                ElementLoaderSemanticsBehaviorInterceptor(libLogger, elementLoaderParams),
                FlakySafeSemanticsBehaviorInterceptor(flakySafetyParams, libLogger),
                FailureLoggingSemanticsBehaviorInterceptor(libLogger)
            )
        } else {
            listOf(
                AutoScrollSemanticsBehaviorInterceptor(libLogger, autoScrollParams),
                ElementLoaderSemanticsBehaviorInterceptor(libLogger, elementLoaderParams),
                FlakySafeSemanticsBehaviorInterceptor(flakySafetyParams, libLogger),
                FailureLoggingSemanticsBehaviorInterceptor(libLogger)
            )
        }

    ComposeInterceptorsInjector.injectKaspressoInKakaoCompose(
        semanticsBehaviorInterceptors,
        semanticsWatcherInterceptors
    )
}
