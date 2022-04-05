package com.template

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

 class WebViewClientUrl: WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        view.loadUrl(request.url.toString())
        return true
    }
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        view.loadUrl(url)
        return true
    }
}