package kr.co.bepo.arcticfoxprogaramming

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Icon
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kr.co.bepo.arcticfoxprogaramming.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var notificationManager: NotificationManager? = null
    private val channelId = "com.ebookfrenzy.directreply.news"

    private val notificationId = 101
    private val KEY_TEXT_REPLY = "key_text_reply"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        notificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(channelId,
            "DirectReply News", "Example News Channel")

        handleIntent()
    }

    private fun handleIntent() {
        val intent = this.intent
        val remoteInput = RemoteInput.getResultsFromIntent(intent)
        if (remoteInput != null) {
            val inputString = remoteInput.getCharSequence(
                KEY_TEXT_REPLY).toString()
            binding.textView.text = inputString

            val repliedNotification = Notification.Builder(this, channelId)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentText("Reply received")
                .build()

            notificationManager?.notify(notificationId,
                repliedNotification)
        }
    }

    private fun createNotificationChannel(id: String,
                                          name: String, description: String) {
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(id, name, importance)

        channel.apply {
            this.description = description
            enableLights(true)
            lightColor = Color.RED
            enableVibration(true)
            vibrationPattern =
                longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
        }

        notificationManager?.createNotificationChannel(channel)
    }

    fun sendNotification(view: View) {
        val replyLabel = "Enter your reply here"
        val remoteInput = RemoteInput.Builder(KEY_TEXT_REPLY)
            .setLabel(replyLabel)
            .build()

        val resultIntent = Intent(this, MainActivity::class.java)

        val resultPendingIntent = PendingIntent.getActivity(
            this,
            0,
            resultIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val icon = Icon.createWithResource(this, android.R.drawable.ic_dialog_info)
        val replyAction = Notification.Action.Builder(
            icon,
            "Reply", resultPendingIntent)
            .addRemoteInput(remoteInput)
            .build()

        val newMessageNotification = Notification.Builder(this, channelId)
            .setColor(ContextCompat.getColor(this, R.color.design_default_color_primary))
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("My Notification")
            .setContentText("This is a test message")
            .addAction(replyAction).build()

        val notificationManager = getSystemService(
            Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(notificationId,
            newMessageNotification)
    }
}