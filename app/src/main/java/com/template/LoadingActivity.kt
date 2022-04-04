package com.template

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.template.WebActivity.Companion.SEARCH_KEY


private lateinit var database: DatabaseReference
private lateinit var baseAnalytics: FirebaseAnalytics

class LoadingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = Firebase.database.reference
        baseAnalytics = Firebase.analytics
        setContentView(R.layout.activity_loading)

        database.addValueEventListener(object : ValueEventListener {
           override fun onDataChange(dataSnapshot: DataSnapshot, ) {
        val db = database.toString()
               val intent = Intent(this@LoadingActivity, WebActivity::class.java)
               intent.putExtra(SEARCH_KEY, db)
               startActivity(intent)
                Log.d("TAG", "Value is: $database ")
                Log.d("TAG", "Value is: $db ")


            }
            override fun onCancelled(error: DatabaseError) {
              val intent = Intent(this@LoadingActivity, MainActivity::class.java)
                startActivity(intent)
            }

        })

//        var btn = findViewById<Button>(R.id.Start)
//
//        btn.setOnClickListener {
//        val intent = Intent(this, WebActivity::class.java)
//        intent.putExtra(SEARCH_KEY, url)
//        startActivity(intent)
//        }
    }

}



