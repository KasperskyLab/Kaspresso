package com.kaspersky.kaspressample.screen

import com.agoda.kakao.web.KWebView
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.web.WebViewActivity
import com.kaspersky.kaspresso.screens.KScreen

object WebViewScreen : KScreen<WebViewScreen>() {

    override val layoutId: Int? = R.layout.activity_webview
    override val viewClass: Class<*>? = WebViewActivity::class.java

    val webView = KWebView {
        withId(R.id.webView)
    }
}