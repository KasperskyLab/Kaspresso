package com.kaspersky.components.composesupport.config

import com.kaspersky.components.composesupport.interceptors.behavior.SemanticsBehaviorInterceptor
import com.kaspersky.components.composesupport.interceptors.tolibrary.ComposeSemanticsInterceptor
import com.kaspersky.components.composesupport.interceptors.watcher.SemanticsWatcherInterceptor
import io.github.kakaocup.compose.KakaoCompose

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
