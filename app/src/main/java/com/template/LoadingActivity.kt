package com.template

import android.R
import android.app.Activity
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


//class SharedPreferencesExample : Activity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.main)
//        val sp = getSharedPreferences(
//            MY_SETTINGS,
//            MODE_PRIVATE
//        )
//        // проверяем, первый ли раз открывается программа
//        val hasVisited = sp.getBoolean("hasVisited", false)
//        if (!hasVisited) {
//            // выводим нужную активность
//            val e: Editor = sp.edit()
//            e.putBoolean("hasVisited", true)
//            e.commit() // не забудьте подтвердить изменения
//        }
//    }
//
//    companion object {
//        private const val MY_SETTINGS = "my_settings"
//    }
//}

        class LoadingActivity : AppCompatActivity() {


                private val MY_SETTINGS = "my_settings"
                private lateinit var database: DatabaseReference
                private lateinit var baseAnalytics: FirebaseAnalytics
                lateinit var url: String
                private lateinit var binding: ActivityLoadingBinding

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                binding = ActivityLoadingBinding.inflate(layoutInflater)
                setContentView(binding.root)
                database = Firebase.database.reference
                baseAnalytics = Firebase.analytics
                shareppref()

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
//                Log.d("url full", url)


                    }

                    override fun onCancelled(error: DatabaseError) {
                        val intent = Intent(this@LoadingActivity, MainActivity::class.java)
                        intent.putExtra("url", url)
                        startActivity(intent)
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
                startWebView()// не забудьте подтвердить изменения
                }

            }
            private fun startWebView() {
                val intent = Intent(this@LoadingActivity, WebActivity::class.java)
            startActivity(intent)
            }

            companion object {
                private const val MY_SETTINGS = "my_settings"
            }
        }








