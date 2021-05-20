package com.twentythirty.guifena.firebase

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.twentythirty.guifena.ui.alert.AlertActivity

/**
 * Created by taufan-mft on 5/19/2021.
 */
class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(p0: String) {
        Log.d("farin", p0)
        super.onNewToken(p0)

    }

    override fun onMessageReceived(p0: RemoteMessage) {
        Log.d("farin", "Message received")
        p0.data.isNotEmpty().let {
            Log.d("farin", p0.data.toString())
            val intent = Intent(this, AlertActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        super.onMessageReceived(p0)
    }
}