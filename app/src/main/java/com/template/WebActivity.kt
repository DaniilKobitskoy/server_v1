package com.template

import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        var tz: TimeZone = TimeZone.getDefault()
        var url: String = intent.getStringExtra(SEARCH_KEY)!!
        var webview = findViewById<WebView>(R.id.webView)
        webview.loadUrl(url +   "/?packageid=$packageName")
        Log.d("tag3", "$url")



    }
    companion object {
        const val SEARCH_KEY = "url"
    }
}
