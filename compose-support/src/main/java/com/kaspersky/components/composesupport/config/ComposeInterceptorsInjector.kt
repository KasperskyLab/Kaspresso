package com.kaspersky.components.composesupport.config

import com.kaspersky.components.composesupport.interceptors.behavior.SemanticsBehaviorInterceptor
import com.kaspersky.components.composesupport.interceptors.tolibrary.ComposeSemanticsInterceptor
import com.kaspersky.components.composesupport.interceptors.watcher.SemanticsWatcherInterceptor
import io.github.kakaocup.compose.KakaoCompose

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

object ComposeInterceptorsInjector {

    fun injectKaspressoInKakaoCompose(
        semanticsBehaviorInterceptors: List<SemanticsBehaviorInterceptor>,
        semanticsWatcherInterceptors: List<SemanticsWatcherInterceptor>
    ) {
        val composeInterceptor =
            ComposeSemanticsInterceptor(
                semanticsBehaviorInterceptors,
                semanticsWatcherInterceptors
            )

        KakaoCompose.intercept {
            onComposeInteraction {
                onCheck(isOverride = true, interceptor = composeInterceptor::interceptCheck)
                onPerform(isOverride = true, interceptor = composeInterceptor::interceptPerform)
            }
        }
    }
}
