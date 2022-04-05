package com.template

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
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
          var APP_PREFERENCES_URL: String = ""
            var mSettings: SharedPreferences? = null
                private val MY_SETTINGS = "my_settings"
                private lateinit var database: DatabaseReference
                private lateinit var baseAnalytics: FirebaseAnalytics
                lateinit var url: String
                private lateinit var binding: ActivityLoadingBinding

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                binding = ActivityLoadingBinding.inflate(layoutInflater)
                setContentView(binding.root)
                database = Firebase.database.reference
                baseAnalytics = Firebase.analytics
                var length: Int = APP_PREFERENCES_URL.trim().length

                if (length > 0){
                    startWebView()

                }else{
                    shareppref()

                }

            }
            private fun shareppref() {
                database.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val db = database.toString()
                        Log.d("TAG", "Value is: $database ")
                        Log.d("TAG", "Value is: $db ")
                        //get package name
                        val packageName = applicationContext.packageName
                        url =
                            "$database/?packageid=$packageName&usserid=${UUID.randomUUID()}&getz=${TimeZone.getDefault()}&getr=utm_source=google-play&utm_medium=organic"
                        var editor: SharedPreferences.Editor = mSettings!!.edit()
                        editor.putString(APP_PREFERENCES_URL, url)
                        editor.apply()
                        Log.d("URL", APP_PREFERENCES_URL)

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
                val intent = Intent(this@LoadingActivity, WebActivity::class.java)
                intent.putExtra("url", APP_PREFERENCES_URL)

            startActivity(intent)
                finish()
            }

//            override fun onStop() {
//                APP_PREFERENCES_URL
//                super.onStop()
//            }
            companion object {
                private const val MY_SETTINGS = "my_settings"
            }
        }








