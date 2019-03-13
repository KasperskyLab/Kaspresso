@file:Suppress("unused")

package com.agoda.kakao.web

import android.support.test.espresso.web.sugar.Web
import com.agoda.kakao.common.KakaoDslMarker
import com.agoda.kakao.common.builders.ViewBuilder
import com.agoda.kakao.configurator.KakaoConfigurator
import com.agoda.kakao.delegates.WebInteractionDelegate

/**
 * Class for interacting with WebViews
 *
 * @param matcher ViewBuilder which will result in matcher of web view
 */
@KakaoDslMarker
open class KWebView(matcher: (ViewBuilder.() -> Unit)? = null) {

    private val web: WebInteractionDelegate = KakaoConfigurator.createWebInteractionDelegate(
            if (matcher != null) {
                Web.onWebView(ViewBuilder().apply(matcher).getViewMatcher())
            } else {
                Web.onWebView()
            }
    )

    /**
     * Operator that allows usage of DSL style
     *
     * @param function WebElementBuilder which will give you access to match elements
     * and perform actions/assertions on it.
     */
    operator fun invoke(function: WebElementBuilder.() -> Unit) {
        WebElementBuilder(web).apply(function)
    }
}
