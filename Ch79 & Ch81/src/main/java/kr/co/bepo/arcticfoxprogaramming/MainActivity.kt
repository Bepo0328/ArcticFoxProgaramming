package kr.co.bepo.arcticfoxprogaramming

import android.app.PendingIntent
import android.app.PictureInPictureParams
import android.app.RemoteAction
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Rational
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kr.co.bepo.arcticfoxprogaramming.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE = 101
    private var mediaController: MediaController? = null
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var TAG = "VideoPlayer"
    private val receiver: BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        configureVideoView()
    }

    private fun configureVideoView() {
        binding.videoView1.setVideoURI(
            Uri.parse(
                "android.resource://" +
                        packageName + "/" + R.raw.movie
            )
        )

        mediaController = MediaController(this)
        mediaController?.setAnchorView(binding.videoView1)

        binding.videoView1.setMediaController(mediaController)
        binding.videoView1.setOnPreparedListener { mp ->
            mp.isLooping = true
            Log.i(TAG, "Duration = " + binding.videoView1.duration)
        }

        binding.videoView1.start()
    }

    fun enterPipMode(view: View) {
        val rational = Rational(binding.videoView1.width, binding.videoView1.height)
        val params = PictureInPictureParams.Builder()
            .setAspectRatio(rational)
            .build()

        binding.pipButton.visibility = View.INVISIBLE
        binding.videoView1.setMediaController(null)
        enterPictureInPictureMode(params)
    }

    override fun onPictureInPictureModeChanged(
        isInPictureInPictureMode: Boolean, newConfig: Configuration?
    ) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig)

        if (isInPictureInPictureMode) {
            val filter = IntentFilter()
            filter.addAction(
                "kr.co.bepo.arcticfoxprogaramming.VIDEO_INFO"
            )

            val receiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context?, intent: Intent?) {
                    Toast.makeText(context, "Favorite Home Movie Clips", Toast.LENGTH_LONG).show()
                }
            }

            registerReceiver(receiver, filter)
            createPipAction()
        } else {
            binding.pipButton.visibility = View.VISIBLE
            binding.videoView1.setMediaController(mediaController)

            receiver?.let {
                unregisterReceiver(it)
            }
        }
    }

    private fun createPipAction() {
        val actions = ArrayList<RemoteAction>()
        val actionIntent = Intent("kr.co.bepo.arcticfoxprogaramming.VIDEO_INFO")

        val pendingIntent = PendingIntent.getBroadcast(
            this,
            REQUEST_CODE, actionIntent, 0
        )

        val icon = Icon.createWithResource(this, R.drawable.ic_info_24dp)
        val remoteAction = RemoteAction(icon, "Info", "Video Info", pendingIntent)
        actions.add(remoteAction)

        val params = PictureInPictureParams.Builder()
            .setActions(actions)
            .build()

        setPictureInPictureParams(params)
    }
}