package com.template

import android.annotation.SuppressLint

import android.content.ContentValues.TAG

import android.util.Log

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
const val channelID = "notification_channel"
const val channelName = "com.template"
class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")

    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if(remoteMessage.notification != null){
            generateNotification(remoteMessage.notification!!.title!!, remoteMessage.notification!!.body!!)
        }
    }

    fun getRemoteView(title: String, message: String) {

    }

    @SuppressLint("UnspecifiedImmutableFlag")
    fun generateNotification(title:String, message:String){

    }

}