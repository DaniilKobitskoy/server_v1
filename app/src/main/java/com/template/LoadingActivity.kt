package com.template

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.template.databinding.ActivityLoadingBinding
import java.util.*


        class LoadingActivity : AppCompatActivity() {
                var APP_PREFERENCES = "mysettings"
                var APP_PREFERENCES_KOLVO: Int = 0
                var mSettings: SharedPreferences? = null
                private val MY_SETTINGS = "my_settings"
                var APP_PREFERENCES_URL: String = ""
                private lateinit var database: DatabaseReference
                private lateinit var baseAnalytics: FirebaseAnalytics
                lateinit var url: String
                private lateinit var binding: ActivityLoadingBinding

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)

                mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
                binding = ActivityLoadingBinding.inflate(layoutInflater)
                setContentView(binding.root)
                database = Firebase.database.reference
                baseAnalytics = Firebase.analytics

                val sharedPreferences: SharedPreferences? = getSharedPreferences(APP_PREFERENCES , Context.MODE_PRIVATE)
                if(sharedPreferences!!.contains(APP_PREFERENCES_URL)) {
                    val url:String = sharedPreferences.getString(APP_PREFERENCES_URL, "").toString()
                    Log.d("URL", url)
                }
//                val sharedPreferences: SharedPreferences? = getSharedPreferences(APP_PREFERENCES , Context.MODE_PRIVATE)
//                val editor: SharedPreferences.Editor = sharedPreferences!!.edit()
//                editor.putInt("kolvo", APP_PREFERENCES_KOLVO)
//                editor.apply()
                if (APP_PREFERENCES_KOLVO > 0){
                    startWebView()

                }else{
                    shareppref()
//                    Log.d("kolvo", APP_PREFERENCES_KOLVO.toString())

                }

            }
            private fun shareppref() {
                database.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val db = database.toString()
                        Log.d("TAG1", "Value is: $database ")
                        Log.d("TAG2", "Value is: $db ")
                        //get package name
                        val packageName = applicationContext.packageName
                        url =
                            "$database/?packageid=$packageName&usserid=${UUID.randomUUID()}&getz=${TimeZone.getDefault()}&getr=utm_source=google-play&utm_medium=organic"

                        Log.d("kolvo", APP_PREFERENCES_KOLVO.toString())
                        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
                        val sharedPreferences: SharedPreferences? = getSharedPreferences(APP_PREFERENCES , Context.MODE_PRIVATE)
                        val editor: SharedPreferences.Editor = sharedPreferences!!.edit()
                        editor.putString("url", url)
                        editor.apply()
                        if(sharedPreferences.contains(APP_PREFERENCES_URL)) {
                            val url:String = sharedPreferences.getString(APP_PREFERENCES_URL, "").toString()
                            Log.d("URL", url)
                        }
//                        val sharedPreferences1: SharedPreferences? = getSharedPreferences(APP_PREFERENCES , Context.MODE_PRIVATE)
//
//                        val editor1: SharedPreferences.Editor = sharedPreferences1!!.edit()
//                        editor1.putInt("kolvo", APP_PREFERENCES_KOLVO)
//                        editor1.apply()
//                        if(sharedPreferences1.contains(APP_PREFERENCES_KOLVO.toString())) {
//                            val kolvo:String = sharedPreferences1.getString(APP_PREFERENCES_KOLVO.toString(), "").toString()
//                            Log.d("kolvo", APP_PREFERENCES_KOLVO.toString())
//                        }

                        startWebView()
                    }

                    override fun onCancelled(error: DatabaseError) {
                        val intent = Intent(this@LoadingActivity, MainActivity::class.java)
                        intent.putExtra("url", url)
                        startActivity(intent)
                        finish()
                    }
                })
                val sp = getSharedPreferences(
                    MY_SETTINGS,
                    Context.MODE_PRIVATE
                )
                // проверяем, первый ли раз открывается программа
                val hasVisited = sp.getBoolean("hasVisited", false)
                if (!hasVisited) {
                    // выводим нужную активность
                    val e: SharedPreferences.Editor = sp.edit()
                    e.putBoolean("hasVisited", true)
                    e.commit()
                    startWebView()
               // не забудьте подтвердить изменения
                }

            }
            private fun startWebView() {


                    val sharedPreferences: SharedPreferences? = getSharedPreferences(APP_PREFERENCES , Context.MODE_PRIVATE)

                    if(sharedPreferences!!.contains(APP_PREFERENCES_URL)) {
                        val url:String = sharedPreferences.getString(APP_PREFERENCES_URL, "").toString()
                        Log.d("URL", url)
                    }
                    val intent = Intent(this@LoadingActivity, WebActivity::class.java)
                    intent.putExtra("url", url)

                    startActivity(intent)
                    finish()


            }

            companion object {
                private const val MY_SETTINGS = "my_settings"
            }

        }










