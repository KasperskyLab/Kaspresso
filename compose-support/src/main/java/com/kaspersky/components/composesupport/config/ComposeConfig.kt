package com.kaspersky.components.composesupport.config

import com.kaspersky.components.composesupport.interceptors.behavior.SemanticsBehaviorInterceptor
import com.kaspersky.components.composesupport.interceptors.behavior.impl.autoscroll.AutoScrollSemanticsBehaviorInterceptor
import com.kaspersky.components.composesupport.interceptors.behavior.impl.elementloader.ElementLoaderSemanticsBehaviorInterceptor
import com.kaspersky.components.composesupport.interceptors.behavior.impl.flakysafety.FlakySafeSemanticsBehaviorInterceptor
import com.kaspersky.components.composesupport.interceptors.behavior.impl.systemsafety.SystemDialogSafetySemanticsBehaviorInterceptor
import com.kaspersky.components.composesupport.interceptors.watcher.SemanticsWatcherInterceptor
import com.kaspersky.components.composesupport.interceptors.watcher.impl.LoggingSemanticsWatcherInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso

class ComposeConfig {

    class Builder {
        lateinit var semanticsWatcherInterceptors: MutableList<SemanticsWatcherInterceptor>
        lateinit var semanticsBehaviorInterceptors: MutableList<SemanticsBehaviorInterceptor>

        companion object {
            fun default(
                kaspressoBuilder: Kaspresso.Builder,
                lateComposeCustomize: Kaspresso.Builder.(Builder) -> Unit
            ): Builder =
                Builder().apply {
                    kaspressoBuilder.defaultInit()
                    kaspressoBuilder.lateComposeCustomize(this)
                }
        }

        private fun Kaspresso.Builder.defaultInit() {
            semanticsWatcherInterceptors = mutableListOf(
                LoggingSemanticsWatcherInterceptor(libLogger)
            )

            semanticsBehaviorInterceptors =
                if (isAndroidRuntime) {
                    mutableListOf(
                        AutoScrollSemanticsBehaviorInterceptor(libLogger, autoScrollParams),
                        SystemDialogSafetySemanticsBehaviorInterceptor(
                            libLogger,
                            instrumentalDependencyProviderFactory.getInterceptorProvider<SystemDialogSafetySemanticsBehaviorInterceptor>(instrumentation),
                            adbServer,
                            systemDialogsSafetyParams
                        ),
                        ElementLoaderSemanticsBehaviorInterceptor(libLogger, elementLoaderParams),
                        FlakySafeSemanticsBehaviorInterceptor(flakySafetyParams, libLogger)
                    )
                } else {
                    mutableListOf(
                        AutoScrollSemanticsBehaviorInterceptor(libLogger, autoScrollParams),
                        ElementLoaderSemanticsBehaviorInterceptor(libLogger, elementLoaderParams),
                        FlakySafeSemanticsBehaviorInterceptor(flakySafetyParams, libLogger)
                    )
                }
        }

        fun build() {
            ComposeInterceptorsInjector.injectKaspressoInKakaoCompose(
                semanticsBehaviorInterceptors,
                semanticsWatcherInterceptors
            )
        }
    }
}
