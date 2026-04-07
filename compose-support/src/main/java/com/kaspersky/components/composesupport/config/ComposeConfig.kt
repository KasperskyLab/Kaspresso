package com.kaspersky.components.composesupport.config

import com.kaspersky.components.composesupport.flakysafety.ComposeFlakySafetyScalper
import com.kaspersky.components.composesupport.interceptors.behavior.SemanticsBehaviorInterceptor
import com.kaspersky.components.composesupport.interceptors.behavior.impl.autoscroll.AutoScrollSemanticsBehaviorInterceptor
import com.kaspersky.components.composesupport.interceptors.behavior.impl.elementloader.ElementLoaderSemanticsBehaviorInterceptor
import com.kaspersky.components.composesupport.interceptors.behavior.impl.flakysafety.FlakySafeSemanticsBehaviorInterceptor
import com.kaspersky.components.composesupport.interceptors.behavior.impl.systemsafety.SystemDialogSafetySemanticsBehaviorInterceptor
import com.kaspersky.components.composesupport.interceptors.watcher.SemanticsWatcherInterceptor
import com.kaspersky.components.composesupport.interceptors.watcher.impl.LoggingSemanticsWatcherInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

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

            val composeFlakySafetyScalper = ComposeFlakySafetyScalper(semanticsBehaviorInterceptors, semanticsWatcherInterceptors)
            externalFlakySafetyScalperNotifier.addScalper(composeFlakySafetyScalper)
        }

        fun build() {
            ComposeInterceptorsInjector.injectKaspressoInKakaoCompose(
                semanticsBehaviorInterceptors,
                semanticsWatcherInterceptors
            )
        }
    }
}
