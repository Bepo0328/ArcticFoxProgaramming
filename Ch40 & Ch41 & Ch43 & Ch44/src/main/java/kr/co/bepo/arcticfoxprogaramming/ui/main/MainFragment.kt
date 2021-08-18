package kr.co.bepo.arcticfoxprogaramming.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import kr.co.bepo.arcticfoxprogaramming.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = MainFragmentBinding.inflate(inflater, container, false)
        .also { _binding = it }
        .root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.application?.let {
            val factory = SavedStateViewModelFactory(it, this)
            viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

            val resultObserver = Observer<Float> { result ->
                binding.resultText.text = result.toString()
            }

            viewModel.getResult().observe(viewLifecycleOwner, resultObserver)

            binding.convertButton.setOnClickListener {
                if (binding.dollarText.text.isNotEmpty()) {
                    viewModel.setAmount(binding.dollarText.text.toString())
                } else {
                    binding.resultText.text = "No Value"
                }
            }
        }
    }
}