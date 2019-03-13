@file:Suppress("unused")

package com.kaspersky.uitest_framework.kakao.web

import android.support.test.espresso.web.sugar.Web
import com.kaspersky.uitest_framework.kakao.common.KakaoDslMarker
import com.kaspersky.uitest_framework.kakao.common.builders.ViewBuilder
import com.kaspersky.uitest_framework.kakao.configuration.Configurator
import com.kaspersky.uitest_framework.kakao.delegates.WebInteractionDelegate

/**
 * Class for interacting with WebViews
 *
 * @param matcher ViewBuilder which will result in matcher of web view
 */
@KakaoDslMarker
open class KWebView(matcher: (ViewBuilder.() -> Unit)? = null) {

    private val web: WebInteractionDelegate = Configurator.webInteractionDelegateFactory.invoke(
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
