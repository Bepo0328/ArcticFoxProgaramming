package kr.co.bepo.arcticfoxprogaramming

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.co.bepo.arcticfoxprogaramming.databinding.MainActivityBinding

class MainActivity : AppCompatActivity(),
    SecondFragment.OnFragmentInteractionListener {

    private val binding: MainActivityBinding by lazy { MainActivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onFragmentInteraction(uri: Uri) {

    }
}