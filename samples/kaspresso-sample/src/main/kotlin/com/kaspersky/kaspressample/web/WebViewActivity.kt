package com.kaspersky.kaspressample.web

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspressample.databinding.ActivityWebviewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)

        with(binding.webView) {
            settings.javaScriptEnabled = true

            loadUrl("file:///android_asset/index.html")
        }

        setContentView(binding.root)
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
