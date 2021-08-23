package kr.co.bepo.arcticfoxprogaramming

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import kr.co.bepo.arcticfoxprogaramming.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE = 101
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val manager: SplitInstallManager by lazy { SplitInstallManagerFactory.create(this) }
    private var mySessionId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun launchFeature(view: View) {
        if (manager.installedModules.contains("my_dynamic_feature")) {
            startActivity(Intent(
                "kr.co.bepo.my_dynamic_feature.MyFeatureActivity"))
        } else {
            binding.statusText.text = "Feature not yet installed"
        }
    }

    fun installFeature(view: View) {

        manager.registerListener(listener)

        val request = SplitInstallRequest.newBuilder()
            .addModule("my_dynamic_feature")
            .build()

        manager.startInstall(request)
            .addOnSuccessListener { sessionId ->
                mySessionId = sessionId
                Toast.makeText(this,
                    "Module installation started",
                    Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this,
                    "Module installation failed: $exception",
                    Toast.LENGTH_SHORT).show()
            }
    }

    @SuppressLint("SetTextI18n")
    private var listener: SplitInstallStateUpdatedListener =
        SplitInstallStateUpdatedListener { state ->
            if (state.sessionId() == mySessionId) {
                when (state.status()) {
                    SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                        binding.statusText.text =
                            "Large Feature Module. Requesting Confirmation"
                        try {
                            manager.startConfirmationDialogForResult(
                                state,
                                this, REQUEST_CODE
                            )
                        } catch (e: IntentSender.SendIntentException) {
                            binding.statusText.text = "Confirmation Request Failed."
                        }
                    }

                    SplitInstallSessionStatus.DOWNLOADING -> {

                    }

                    SplitInstallSessionStatus.INSTALLING ->
                        binding.statusText.text = "Installing feature"


                    SplitInstallSessionStatus.DOWNLOADED ->
                        binding.statusText.text = "Download Complete"

                    SplitInstallSessionStatus.INSTALLED ->
                        binding.statusText.text = "Installed - Feature is ready"

                    SplitInstallSessionStatus.CANCELED ->
                        binding.statusText.text = "Installation cancelled"

                    SplitInstallSessionStatus.PENDING ->
                        binding.statusText.text = "Installation pending"

                    SplitInstallSessionStatus.FAILED ->
                        binding.statusText.text =
                            String.format(Locale.getDefault(),
                                "Installation Failed. Error code: %s", state.errorCode())
                }
            }
        }

    override fun onResume() {
        manager.registerListener(listener)
        super.onResume()
    }

    override fun onPause() {
        manager.unregisterListener(listener)
        super.onPause()
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                binding.statusText.text = "Beginning Installation."
            } else {
                binding.statusText.text = "User declined Installation."
            }
        }
    }

    fun deleteFeature(view: View) {
        manager.deferredUninstall(listOf("my_dynamic_feature"))
    }
}