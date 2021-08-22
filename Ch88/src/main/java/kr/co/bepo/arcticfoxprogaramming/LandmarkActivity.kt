package kr.co.bepo.arcticfoxprogaramming

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kr.co.bepo.arcticfoxprogaramming.databinding.ActivityLandmarkBinding

class LandmarkActivity : AppCompatActivity() {

    private val binding: ActivityLandmarkBinding by lazy { ActivityLandmarkBinding.inflate(layoutInflater) }
    private var landmark: Landmark? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val appLinkIntent = intent
        val appLinkAction = appLinkIntent.action
        val appLinkData = appLinkIntent.data

        if (appLinkAction != null) {
            if (appLinkAction == "android.intent.action.VIEW") {
                val landmarkId = appLinkData?.lastPathSegment

                if (landmarkId != null) {
                    displayLandmark(landmarkId)
                }
            }
        } else {
            handleIntent(appLinkIntent)
        }
    }

    private fun handleIntent(intent: Intent) {
        val landmarkId = intent.getStringExtra(AppLinkingActivity.LANDMARK_ID)
        displayLandmark(landmarkId!!)
    }

    fun deleteLandmark(view: View) {
        val dbHandler = MyDBHandler(this, null, null, 1)

        if (landmark != null) {
            dbHandler.deleteLandmark(landmark?.id)
            binding.titleText.text = ""
            binding.descriptionText.text = ""
            binding.deleteButton.isEnabled = false
        }
    }

    @SuppressLint("SetTextI18n")
    private fun displayLandmark(landmarkId: String) {
        val dbHandler = MyDBHandler(this, null, null, 1)

        landmark = dbHandler.findLandmark(landmarkId)

        if (landmark != null) {
            binding.deleteButton.isEnabled = landmark?.personal != 0

            binding.titleText.text = landmark?.title
            binding.descriptionText.text = landmark?.description
        } else {
            binding.descriptionText.text = "No Match Found"
        }
    }

}