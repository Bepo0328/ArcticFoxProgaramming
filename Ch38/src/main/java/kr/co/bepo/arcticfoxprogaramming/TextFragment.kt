package kr.co.bepo.arcticfoxprogaramming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.co.bepo.arcticfoxprogaramming.databinding.FragmentTextBinding

class TextFragment : Fragment() {

    private var _binding: FragmentTextBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentTextBinding.inflate(inflater, container, false)
        .also { _binding = it }
        .root

    fun changeTextProperties(fontSize: Int, text: String) {
        binding.textView2.textSize = fontSize.toFloat()
        binding.textView2.text = text
    }

}