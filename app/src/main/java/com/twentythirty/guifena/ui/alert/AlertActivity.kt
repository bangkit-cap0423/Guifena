package com.twentythirty.guifena.ui.alert

import android.app.KeyguardManager
import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.twentythirty.guifena.R


class AlertActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var wakeLock: PowerManager.WakeLock
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert)
        val win: Window = window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true)
            setTurnScreenOn(true)
            val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
            keyguardManager.requestDismissKeyguard(this, null)
        } else {
            this.window.addFlags(
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
            )
        }
        //  wakeLock =
        //      (getSystemService(Context.POWER_SERVICE) as PowerManager).run {
        //          newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyApp::MyWakelockTag").apply {
        //            acquire()
        //          }
        //      }
        mediaPlayer = MediaPlayer.create(this, R.raw.alert)
        mediaPlayer.start()
    }

    override fun onPause() {
        super.onPause()
        Log.d("farin", "onpause")
        mediaPlayer.stop()
        //  wakeLock.release()
    }

    override fun onResume() {
        super.onResume()
        Log.d("farin", "onresume")
        if (!mediaPlayer.isPlaying) {
            mediaPlayer = MediaPlayer.create(this, R.raw.alert)
            mediaPlayer.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        //     wakeLock.release()
    }
}