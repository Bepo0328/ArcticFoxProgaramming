package kr.co.bepo.arcticfoxprogaramming

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun convertCurrency(view: View) {
        if (dollar_text.text.isNotEmpty()) {
            val dollarValue = dollar_text.text.toString().toFloat()
            val euroValue = dollarValue * 0.85f
            text_view.text = euroValue.toString()
        } else {
            text_view.text = getString(R.string.no_value)
        }
    }

    fun sendText(view: View) {}
    fun sendText(view: View) {}
    fun sendText(view: View) {}
    fun showWebPage(view: View) {}
    fun showWebPage(view: View) {}
    fun buttonClick(view: View) {}
    fun showTime(view: View) {}
    fun sendMessage(view: View) {}
    fun sendNotification(view: View) {}
    fun sendNotification(view: View) {}
    fun newFile(view: View) {}
    fun openFile(view: View) {}
    fun saveFile(view: View) {}
    fun enterPipMode(view: View) {}
    fun playAudio(view: View) {}
    fun recordAudio(view: View) {}
    fun stopAudio(view: View) {}
    fun printDocument(view: View) {}
}