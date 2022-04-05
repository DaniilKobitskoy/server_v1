package com.template

import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.template.R
import java.util.*


class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        var url: String = intent.getStringExtra("url")!!
        var webview = findViewById<WebView>(R.id.webView)
        webview.webViewClient = WebViewClientUrl()
        webview.getSettings().setJavaScriptEnabled(true)
//webview.loadUrl(url +   "/?packageid=$packageName")
Log.d("tagUrl", url)
        webview.loadUrl(url)



    }

    override fun onBackPressed() {
        var webview = findViewById<WebView>(R.id.webView)
        if (webview.canGoBack()) {
            webview.goBack()
        } else {
            super.onBackPressed()
        }
    }



}
