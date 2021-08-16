package kr.co.bepo.arcticfoxprogaramming

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.co.bepo.arcticfoxprogaramming.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.myButton.setOnClickListener {
            binding.statusText.text = "Button clicked"
        }

        binding.myButton.setOnLongClickListener {
            binding.statusText.text = "Long Button click"
            false
        }
    }
}