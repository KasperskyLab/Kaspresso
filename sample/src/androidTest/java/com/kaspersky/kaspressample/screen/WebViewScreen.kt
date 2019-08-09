package com.kaspersky.kaspressample.screen

import com.agoda.kakao.screen.Screen
import com.agoda.kakao.web.KWebView
import com.kaspersky.kaspressample.R

object WebViewScreen : Screen<WebViewScreen>() {

    val webView = KWebView {
        withId(R.id.webView)
    }
}