package kr.co.bepo.arcticfoxprogaramming

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kr.co.bepo.arcticfoxprogaramming.databinding.ActivityMainBinding
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        handleIntent()
    }

    fun showWebPage(view: View) {
        val intent = Intent(Intent.ACTION_VIEW,
            Uri.parse("https://www.ebookfrenzy.com"))
        startActivity(intent)
    }

    private fun handleIntent() {
        val intent = this.intent
        val data = intent.data
        var url: URL? = null
        try {
            url = URL(data?.scheme,
                data?.host,
                data?.path)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        binding.webView1.loadUrl(url?.toString() ?: "https://www.amazon.com")
    }

}