package com.kaspersky.kaspresso_allure_support_sample.screen

import com.kaspersky.kaspresso_allure_support_sample.R
import com.kaspersky.kaspresso_sample_core.web.WebViewActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.web.KWebView

object WebViewScreen : KScreen<WebViewScreen>() {

    override val layoutId: Int? = R.layout.activity_webview
    override val viewClass: Class<*>? = WebViewActivity::class.java

    val webView = KWebView {
        withId(R.id.webView)
    }
}
