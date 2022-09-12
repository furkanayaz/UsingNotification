package com.fa.usingnotification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.fa.usingnotification.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.activityMainObject = this
        setContentView(binding.root)



    }

    @SuppressLint("UnspecifiedImmutableFlag")
    fun createNotification() {
        val builder: NotificationCompat.Builder
        val notificationService = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "channelId"
        val channelName = "channelName"
        val randomInt = Random.nextInt(0, 1000)

        val intent = Intent(this, MainActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            var channel: NotificationChannel? = notificationService.getNotificationChannel(channelId)

            if (channel == null) {
                channel = NotificationChannel(channelId, channelName, importance)
                notificationService.createNotificationChannel(channel)
            }

            builder = NotificationCompat.Builder(this, channelId)
            builder.setContentTitle("TITLE")
            builder.setContentText("CONTENT TEXT")
            builder.setSmallIcon(R.drawable.games)
            builder.setContentIntent(pendingIntent)
            builder.setAutoCancel(true)

        }else {
            builder = NotificationCompat.Builder(this)
            builder.setContentTitle("TITLE")
            builder.setContentText("CONTENT TEXT")
            builder.setSmallIcon(R.drawable.games)
            builder.setContentIntent(pendingIntent)
            builder.setAutoCancel(true)
        }

        notificationService.notify(randomInt, builder.build())
    }

}