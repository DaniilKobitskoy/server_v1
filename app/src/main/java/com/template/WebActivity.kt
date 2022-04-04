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
//        val scanner = Scanner(System.`in`)
//        while (true) {
//            val line: String = scanner.nextLine().trim()
//            if (line == "uuidcreaterandom") {
//                val uuid: UUID = UUID.randomUUID()
//                val strokeid: String = uuid.toString()
//                println(strokeid)
//            }
//        }
var url: String = intent.getStringExtra(SEARCH_KEY)!!
        var webview = findViewById<WebView>(R.id.webView)
webview.loadUrl(url +   "/?packageid=$packageName")
        Log.d("tag3", "$url")



    }
    companion object {
        const val SEARCH_KEY = "url"
    }
}
