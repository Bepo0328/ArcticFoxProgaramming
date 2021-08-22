package kr.co.bepo.arcticfoxprogaramming

import android.content.Context
import android.os.Bundle
import android.print.PrintAttributes
import android.print.PrintManager
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import kr.co.bepo.arcticfoxprogaramming.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
    }

    override fun onStart() {
        super.onStart()
        configureWebView()
    }

    private fun configureWebView() {
        binding.contentMain.myWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?, request: WebResourceRequest?): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
        binding.contentMain.myWebView.loadUrl(
            "https://www.amazon.com/")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_print -> {
                createWebPrintJob(binding.contentMain.myWebView)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun createWebPrintJob(webView: WebView?) {
        val printManager = this
            .getSystemService(Context.PRINT_SERVICE) as PrintManager
        val printAdapter = webView?.createPrintDocumentAdapter("MyDocument")
        val jobName = getString(R.string.app_name) + " Print Test"

        printAdapter?.let {
            printManager.print(
                jobName, it,
                PrintAttributes.Builder().build()
            )
        }
    }
}