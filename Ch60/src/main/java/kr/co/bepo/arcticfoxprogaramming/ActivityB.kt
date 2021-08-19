package kr.co.bepo.arcticfoxprogaramming

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kr.co.bepo.arcticfoxprogaramming.databinding.ActivityBBinding

class ActivityB : AppCompatActivity() {

    private val binding: ActivityBBinding by lazy { ActivityBBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val extras = intent.extras ?: return

        val qString = extras.getString("qString")
        binding.textView2.text = qString
    }

    fun returnText(view: View) {
        finish()
    }

    override fun finish() {
        val data = Intent()
        val returnString = binding.editText2.text.toString()
        data.putExtra("returnData", returnString)
        setResult(RESULT_OK, data)
        super.finish()
    }

}