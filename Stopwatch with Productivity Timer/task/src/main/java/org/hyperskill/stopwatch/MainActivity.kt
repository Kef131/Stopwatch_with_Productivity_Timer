package org.hyperskill.stopwatch

import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.concurrent.thread

const val CHANNEL_ID = "org.hyperskill"
lateinit var notificationManager: NotificationManager
lateinit var notificationBuilder: NotificationCompat.Builder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val handler = Handler(Looper.getMainLooper())
        var startTime = LocalTime.of(0, 0, 0)
        var startButtonClicked = false
        val formatMinSec = DateTimeFormatter.ofPattern("mm:ss")
        var upperLimit = 0
        notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var notificationIssued = false
        progressBar.indeterminateTintList = ColorStateList.valueOf(Color.BLACK)
        val timer = Runnable {
            thread {
                while (startButtonClicked) {
                    Thread.sleep(900)
                    val random = Random()
                    val color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
                    startTime = startTime.plusSeconds(1)
                    handler.post {
                        progressBar.indeterminateTintList = ColorStateList.valueOf(color)
                        textView.text = startTime.format(formatMinSec)
                        if (upperLimit != 0 && startTime.second > upperLimit) {
                            textView.setTextColor(Color.RED)
                            if (!notificationIssued) notificationManager.notify(393939, notificationBuilder.build()); notificationIssued = true
                        }
                    }
                }
            }
        }

        settingsButton.setOnClickListener {
            if(!startButtonClicked) {
                    val contentView = LayoutInflater.from(this).inflate(R.layout.custom_dialog_view, null, false)
                    AlertDialog.Builder(this)
                        .setTitle("Set upper limit in seconds")
                        .setView(contentView)
                        .setPositiveButton("OK") { _, _ ->
                            val editText = contentView.findViewById<EditText>(R.id.upperLimitEditText)
                            if (editText.text.toString() != "") upperLimit = editText.text.toString().toInt()
                        }
                        .setNegativeButton("Cancel", null)
                        .show()
            }
        }

        startButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            if (!startButtonClicked) {
                startButtonClicked = true
                handler.post(timer)
            }
            settingsButton.isEnabled = false
        }

        resetButton.setOnClickListener {
            progressBar.visibility = View.INVISIBLE
            startButtonClicked = false
            handler.removeCallbacks(timer)
            textView.text = "00:00"
            startTime = LocalTime.of(0, 0, 0)
            settingsButton.isEnabled = true
            textView.setTextColor(Color.BLACK)
            notificationIssued = false
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Time Status"
            val descriptionText = "Time alerts"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Notification")
            .setContentText("Time exceeded")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setTimeoutAfter(5000)
    }
}