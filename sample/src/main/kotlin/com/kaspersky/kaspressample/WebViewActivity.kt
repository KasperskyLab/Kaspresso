package com.kaspersky.kaspressample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_webview.*
import android.webkit.WebView
import android.webkit.WebResourceRequest
import android.os.Build
import android.annotation.TargetApi

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        with(webView) {
            webViewClient = object : WebViewClient() {

                @TargetApi(Build.VERSION_CODES.N)
                override fun shouldOverrideUrlLoading(
                    view: WebView,
                    request: WebResourceRequest
                ): Boolean {

                    view.loadUrl(request.url.toString())
                    return true
                }

                // Для старых устройств
                override fun shouldOverrideUrlLoading(
                    view: WebView,
                    url: String
                ): Boolean {

                    view.loadUrl(url)
                    return true
                }
            }

            settings.javaScriptEnabled = true

            loadUrl("https://my.kaspersky.com/ru/")
        }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}