package com.kaspersky.components.composesupport.flakysafety

import com.kaspersky.components.composesupport.config.ComposeInterceptorsInjector
import com.kaspersky.components.composesupport.interceptors.behavior.SemanticsBehaviorInterceptor
import com.kaspersky.components.composesupport.interceptors.behavior.impl.flakysafety.FlakySafeSemanticsBehaviorInterceptor
import com.kaspersky.components.composesupport.interceptors.watcher.SemanticsWatcherInterceptor
import com.kaspersky.kaspresso.flakysafety.scalpel.external.ExternalFlakySafetyScalper

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
