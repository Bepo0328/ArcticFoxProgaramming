package kr.co.bepo.arcticfoxprogaramming

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kr.co.bepo.arcticfoxprogaramming.databinding.ActivityAppLinkingBinding

class AppLinkingActivity : AppCompatActivity() {

    companion object {
        val LANDMARK_ID = "landmarkID"
        private val TAG = "AppIndexActivity"
        private val PERSONAL = 1
        private val PUBLIC = 0
    }

    private val binding: ActivityAppLinkingBinding by lazy { ActivityAppLinkingBinding.inflate(layoutInflater) }

    private var dbHandler: MyDBHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        dbHandler = MyDBHandler(this, null, null, 1)
    }

    @SuppressLint("SetTextI18n")
    fun findLandmark(view: View) {
        if (binding.idText.text.toString() != "") {
            val landmark = dbHandler?.findLandmark(binding.idText.text.toString())

            if (landmark != null) {
                val uri = Uri.parse("http://example.com/landmarks/" + landmark.id)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            } else {
                binding.titleText.setText("No Match")
            }
        }
    }

    fun addLandmark(view: View) {
        val landmark = Landmark(binding.idText.text.toString(), binding.titleText.text.toString(),
            binding.descriptionText.text.toString(), 1)

        dbHandler?.addLandmark(landmark)
        binding.idText.setText("")
        binding.titleText.setText("")
        binding.descriptionText.setText("")
    }
}