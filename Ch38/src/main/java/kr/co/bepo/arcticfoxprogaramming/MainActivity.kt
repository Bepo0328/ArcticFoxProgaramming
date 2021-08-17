package kr.co.bepo.arcticfoxprogaramming

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import kr.co.bepo.arcticfoxprogaramming.databinding.ActivityMainBinding

class MainActivity : FragmentActivity(),
                     ToolbarFragment.ToolbarListener {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onButtonClick(position: Int, text: String) {
        val textFragment = supportFragmentManager.findFragmentById(R.id.text_fragment) as TextFragment
        textFragment.changeTextProperties(position, text)
    }
}