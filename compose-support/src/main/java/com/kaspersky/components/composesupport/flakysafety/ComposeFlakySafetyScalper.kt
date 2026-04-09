package com.kaspersky.components.composesupport.flakysafety

import com.kaspersky.components.composesupport.config.ComposeInterceptorsInjector
import com.kaspersky.components.composesupport.interceptors.behavior.SemanticsBehaviorInterceptor
import com.kaspersky.components.composesupport.interceptors.behavior.impl.flakysafety.FlakySafeSemanticsBehaviorInterceptor
import com.kaspersky.components.composesupport.interceptors.watcher.SemanticsWatcherInterceptor
import com.kaspersky.kaspresso.flakysafety.scalpel.external.ExternalFlakySafetyScalper

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

/**
 * Removes and restores compose flaky safety interceptor so the `flakySafely` expression works correctly
 * @see com.kaspersky.kaspresso.flakysafety.scalpel.FlakySafeInterceptorScalpel
 */
internal class ComposeFlakySafetyScalper(
    private val semanticsBehaviorInterceptors: MutableList<SemanticsBehaviorInterceptor>,
    private val semanticsWatcherInterceptors: MutableList<SemanticsWatcherInterceptor>,
) : ExternalFlakySafetyScalper {
    override fun isFlakySafetyInterceptorPresent(): Boolean {
        return semanticsBehaviorInterceptors.any { it is FlakySafeSemanticsBehaviorInterceptor }
    }

    override fun scalpFlakySafety() {
        val interceptors = semanticsBehaviorInterceptors.filter { it !is FlakySafeSemanticsBehaviorInterceptor }
        ComposeInterceptorsInjector.injectKaspressoInKakaoCompose(interceptors, semanticsWatcherInterceptors)
    }

    override fun restoreFlakySafety() {
        ComposeInterceptorsInjector.injectKaspressoInKakaoCompose(semanticsBehaviorInterceptors, semanticsWatcherInterceptors)
    }
}
